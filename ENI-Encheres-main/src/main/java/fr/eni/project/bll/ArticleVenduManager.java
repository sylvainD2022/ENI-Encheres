package fr.eni.project.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Retrait;
import fr.eni.project.dal.dao.ArticleVenduDAO;
import fr.eni.project.dal.dao.DAOFactory;

public class ArticleVenduManager {
	private ArticleVenduDAO articleVenduDAO = DAOFactory.getArticleVenduDAO();

	public void addArticleVendu(ArticleVendu article) {

		try {
			this.articleVenduDAO.addArticleVendu(article);
		} catch (SQLException e) {
			e.printStackTrace(); // e.printStackTrace(); : ca va afficher en console la pile d'erreur (utile pour
									// debbugage)

		}
	}

	public List<ArticleVendu> getAll() {
		try {
			return this.articleVenduDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setArticle(ArticleVendu article) {
		try {
			this.articleVenduDAO.setArticle(article);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteArticle(int noArticle) {
		try {
			this.articleVenduDAO.deleteArticle(noArticle);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArticleVendu getArticleVendu(int noArticle) {
		try {
			return this.articleVenduDAO.getArticleVendu(noArticle);
		} catch (SQLException e) {
			return null;
		}

	}

	public Retrait getRetrait(int noArticle) {
		try {
			return this.articleVenduDAO.getRetrait(noArticle);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	public void addRetrait(Retrait retrait) {
		try {
			this.articleVenduDAO.addRetrait(retrait);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void setRetrait(Retrait retrait) {
		try {
			this.articleVenduDAO.setRetrait(retrait);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void deleteRetrait(int noArticle) {
		try {
			this.articleVenduDAO.deleteRetrait(noArticle);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
