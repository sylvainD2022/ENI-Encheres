package fr.eni.project.dal.dao;

import fr.eni.project.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.project.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.project.dal.jdbc.EnchereDAOJdbcImpl;
import fr.eni.project.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {
	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOJdbcImpl();
	}

	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}

	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}

	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
}
