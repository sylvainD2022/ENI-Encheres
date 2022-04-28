package fr.eni.project.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.project.bll.ArticleVenduManager;
import fr.eni.project.bll.EnchereManager;
import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Enchere;

/**
 * Servlet implementation class DeleteEnchereServlet
 */
@WebServlet("/DeleteEnchereServlet")
public class DeleteEnchereServlet extends HttpServlet {
	EnchereManager enchereDel = new EnchereManager();
	ArticleVenduManager articleDel = new ArticleVenduManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int noArticle = Integer.parseInt(request.getParameter("delete"));
		articleDel.deleteRetrait(noArticle);
		enchereDel.deleteEnchere(noArticle);
		articleDel.deleteArticle(noArticle);

		request.getRequestDispatcher("/accueil").forward(request, response);
	}

}
