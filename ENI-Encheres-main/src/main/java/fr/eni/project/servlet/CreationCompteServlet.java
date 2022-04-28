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

@WebServlet("/creerCompte")
public class CreationCompteServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/creerCompte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String mot_de_passe = request.getParameter("mot_de_passe");
		String confirm = request.getParameter("confirm");
		request.setAttribute("pseudo", request.getParameter("pseudo"));
		request.setAttribute("nom", request.getParameter("nom"));
		request.setAttribute("prenom", request.getParameter("prenom"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("tel", request.getParameter("tel"));
		request.setAttribute("rue", request.getParameter("rue"));
		request.setAttribute("codepostal", request.getParameter("codepostal"));
		request.setAttribute("ville", request.getParameter("ville"));

		if (!mot_de_passe.equals(confirm)) {
			request.setAttribute("isException", true);
			request.setAttribute("message", "Les mots de passe ne correspondent pas");
			request.getRequestDispatcher("WEB-INF/creerCompte.jsp").forward(request, response);
		} else {

			UtilisateurManager utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, tel, rue, codepostal, ville,
					mot_de_passe);
			utilisateurManager.addUtilisateur(utilisateur);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			session.setAttribute("utilisateurConnecte", true);
			response.sendRedirect(request.getContextPath() + "/accueil");
		}
	}
}
