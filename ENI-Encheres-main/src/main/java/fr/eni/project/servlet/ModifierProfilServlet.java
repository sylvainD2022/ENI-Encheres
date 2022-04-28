package fr.eni.project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.project.bll.BLLException;
import fr.eni.project.bll.UtilisateurManager;
import fr.eni.project.bo.Utilisateur;

@WebServlet("/modifierProfil")
public class ModifierProfilServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur") == null) {
			response.sendRedirect(request.getContextPath() + "/accueil");
		} else {
			request.getRequestDispatcher("WEB-INF/modifierProfil.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		request.setAttribute("message", request.getAttribute("message"));
		String tache = request.getParameter("tache");

		if (tache.equalsIgnoreCase("Enregistrer")) {
			String pseudo = request.getParameter("pseudo");
			String newPseudo = request.getParameter("newPseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String rue = request.getParameter("rue");
			String codepostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");
			String mot_de_passe = request.getParameter("mot_de_passe");
			String newMDP = request.getParameter("newMDP");
			String confirm = request.getParameter("confirm");
			if (!(mot_de_passe.equals(utilisateur.getMot_de_passe()))) {
				request.setAttribute("isException", true);
				request.setAttribute("message", "le mot de passe incorrect.");
				this.doGet(request, response);
			} else if (confirm.equals(newMDP)) {
				utilisateur = new Utilisateur(newPseudo, nom, prenom, email, tel, rue, codepostal, ville, newMDP);
				utilisateurManager.upgradeUtilisateur(utilisateur, pseudo, mot_de_passe);
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				session.setAttribute("connecte", true);
				response.sendRedirect(request.getContextPath() + "/accueil");
			} else {
				request.setAttribute("isException", true);
				request.setAttribute("message", "Le nouveau mot de passe et sa confirmation differents");
				this.doGet(request, response);
			}
		}

		Integer no_utilisateur = Integer.valueOf(request.getParameter("NoUtilisateur"));

		if (tache.equalsIgnoreCase("Supprimer le compte")) {
			utilisateurManager.DeleteToArchive(no_utilisateur);
			response.sendRedirect(request.getContextPath() + "/accueil?deconnect=1");
		}
	}

}
