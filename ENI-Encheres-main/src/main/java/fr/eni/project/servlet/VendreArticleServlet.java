package fr.eni.project.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.project.bll.ArticleVenduManager;
import fr.eni.project.bll.CategorieManager;
import fr.eni.project.bll.UtilisateurManager;
import fr.eni.project.bo.ArticleVendu;

import fr.eni.project.bo.Categorie;
import fr.eni.project.bo.Retrait;

/**
 * Servlet implementation class vendreArticleServlet
 */
@WebServlet("/vendreArticle")
public class VendreArticleServlet extends HttpServlet {

	ArticleVenduManager articleManager = new ArticleVenduManager();
	CategorieManager categorieManager = new CategorieManager();
	UtilisateurManager utilisateurManager = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		request.setAttribute("listeCategorie", categorieManager.getAll());
		request.getRequestDispatcher("WEB-INF/nouvelleVente.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String creation = request.getParameter("valideCreationVente");

		String nomArticle = request.getParameter("nomArticle");
		String descriptionArt = request.getParameter("description");
		int categorie = Integer.parseInt(request.getParameter("categorie"));// car on récupère le no_catégorie
		int miseAPrix = Integer.parseInt(request.getParameter("prix_initial"));

		String strDateTime_Debut = request.getParameter("debutEnchere");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime debutVente = LocalDateTime.parse(strDateTime_Debut, formatter);
		String strDateTime_Fin = request.getParameter("finEnchere");
		LocalDateTime finVente = LocalDateTime.parse(strDateTime_Fin, formatter);

		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");

		if (creation.equalsIgnoreCase("ok")) {
			Categorie categ = categorieManager.getOne(categorie);

			ArticleVendu article = new ArticleVendu();
			article.setNomArticle(nomArticle);
			article.setDescription(descriptionArt);
			article.setCategorie(categ);
			article.setMiseAPrix(miseAPrix);
			article.setDateDebutEncheres(debutVente);
			article.setDateFinEncheres(finVente);

			articleManager.addArticleVendu(article); // j'ajoute mon article en BDD

			Retrait retrait = new Retrait(article);
			retrait.setRue(rue);
			retrait.setCode_postal(cp);
			retrait.setVille(ville);

			articleManager.addRetrait(retrait);// j'ajoute mon retrait
		}
		request.getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

}
