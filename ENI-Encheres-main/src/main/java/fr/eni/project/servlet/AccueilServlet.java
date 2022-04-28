package fr.eni.project.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.project.bll.ArticleVenduManager;

import fr.eni.project.bll.CategorieManager;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		CategorieManager cm = new CategorieManager();
		request.setAttribute("listeCategorie", cm.getAll());

		if (request.getParameter("deconnect") != null) {
			request.getSession().setAttribute("utilisateur", null);
			request.getSession().setAttribute("utilisateurConnecte", false);
		}
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
