package fr.eni.project.servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.project.bll.ArticleVenduManager;
import fr.eni.project.bll.EnchereManager;
import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Enchere;
import fr.eni.project.bo.Utilisateur;

/**
 * @author sdubuisson2022 Servlet implementation class
 *         EnchereRetourAccueilServlet
 */
@WebServlet("/enchereRetourAccueilServlet")
public class EnchereRetourAccueilServlet extends HttpServlet {
	private ArticleVenduManager managerArticle = new ArticleVenduManager();
	private EnchereManager managerEnchere = new EnchereManager();

	/**
	 * @param prixArticleConcerne : montant de l'enchère la plus haute avant la
	 *                            validation de la nouvelle proposition
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateurConnecte");
		int solde = user.getCredit();
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleVendu article = this.managerArticle.getArticleVendu(noArticle);// je récupère l'article

		Utilisateur vendeur = article.getProprio();

		Enchere enchere = this.managerEnchere.getEnchereMax(vendeur.getNoUtilisateur(), article.getNoArticle());// je
																												// récupère
																												// l'enchere

		if (proposition > solde) {

			System.err.println("l'enchère NE DOIS PAS dépasser le crédit !!!!!!!!!!!!!!");

		} else if (proposition < article.getPrixVente()) {

			System.err.println("l'enchère NE DOIS PAS être inférieur au prix de vente qui est : "
					+ article.getPrixVente() + "Points !!!!!!");

		} else {

			enchere.setMontant_enchere(proposition);
			enchere.setDateEnchere(LocalDateTime.now());
			enchere.setAcheteur(user);

			this.managerEnchere.addEnchere(enchere);

			article.setPrixVente(proposition);
			this.managerArticle.setArticle(article);// modification en bbd de l'article

		}

		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
