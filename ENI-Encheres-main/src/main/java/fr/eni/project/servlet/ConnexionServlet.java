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

@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String MDP = request.getParameter("MDP");
		Boolean utilisateurConnecte = false;
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur;

		utilisateur = utilisateurManager.connecterUtilisateur(identifiant, MDP);
		if (utilisateur.getNoUtilisateur() != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			session.setAttribute("utilisateurConnecte", true);
			response.sendRedirect(request.getContextPath() + "/accueil");
		}
	}

}
