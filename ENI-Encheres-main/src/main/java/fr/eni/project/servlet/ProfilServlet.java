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

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/profil")
public class ProfilServlet extends HttpServlet {

	private UtilisateurManager utilisateurManager;

	public void init() throws ServletException {
		utilisateurManager = new UtilisateurManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur") == null) {
			response.sendRedirect(request.getContextPath() + "/accueil");
		} else {

			int noProfil = 0;

			// on r�cup�re un id profil depuis header.jsp s'il existe
			if (!(request.getParameter("noUtilisateur") == null)) {
				noProfil = Integer.parseInt(request.getParameter("noProfil"));
			}
			Utilisateur user = null;
			user = utilisateurManager.getUtilisateurByID(noProfil);

			// Redirection vers JSP
			request.setAttribute("userprofil", user);
			request.getRequestDispatcher("WEB-INF/afficherProfil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
