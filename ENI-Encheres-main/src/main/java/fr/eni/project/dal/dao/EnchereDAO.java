package fr.eni.project.dal.dao;

import java.sql.SQLException;
import java.util.List;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Enchere;

import fr.eni.project.bo.Retrait;
import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.DALException;

public interface EnchereDAO {
	public List<Enchere> getAll(ArticleVendu article, Utilisateur user) throws SQLException;

	public Enchere getEnchereMax(int noUtilisateur, int noArticle) throws SQLException, DALException;

	public List<Enchere> getMesEncheresWin(int noUtilisateur) throws SQLException, DALException;

	public void addEnchere(Enchere enchere) throws SQLException;

	public void deleteEnchere(int noArticle) throws SQLException;

	public List<Enchere> getEnchereByArt(int noArticle) throws SQLException, DALException;

	public void setEnchere(Enchere enchere) throws SQLException;

	public Enchere getEnchereByArtAndUser(int noUtilisateur, int noArticle) throws SQLException, DALException;

	public void enchereToArchive(int no_utilisateur) throws DALException;

}
