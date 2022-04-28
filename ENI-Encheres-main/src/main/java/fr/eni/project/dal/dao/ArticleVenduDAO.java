package fr.eni.project.dal.dao;

import java.sql.SQLException;
import java.util.List;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Retrait;
import fr.eni.project.dal.DALException;

public interface ArticleVenduDAO {
	public List<ArticleVendu> getAll() throws SQLException;

	public void addArticleVendu(ArticleVendu article) throws SQLException;

	public void setArticle(ArticleVendu article) throws SQLException;

	public void setPrix_vente(ArticleVendu article) throws SQLException;

	public void deleteArticle(int noArticle) throws SQLException;

	public ArticleVendu getArticleVendu(int noArticle) throws SQLException;

	public Retrait getRetrait(int noArticle) throws SQLException;

	public void addRetrait(Retrait retrait) throws SQLException;

	public void setRetrait(Retrait retrait) throws SQLException;

	public void deleteRetrait(int noArticle) throws SQLException;

	public void articleToArchive(int no_utilisateur) throws DALException;

}
