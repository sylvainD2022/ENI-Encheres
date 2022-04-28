package fr.eni.project.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Enchere;
import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.DALException;
import fr.eni.project.dal.dao.DAOFactory;
import fr.eni.project.dal.dao.EnchereDAO;

public class EnchereManager {
	private EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();

	public List<Enchere> getAll(ArticleVendu article, Utilisateur User) {
		try {
			return this.enchereDAO.getAll(article, User);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	public Enchere getEnchereMax(int noUtilisateur, int noArticle) {

		try {
			return this.enchereDAO.getEnchereMax(noUtilisateur, noArticle);

		} catch (SQLException | DALException e) {

			e.printStackTrace();
			return null;
		}

	}

	public List<Enchere> getEnchereByArt(int noArticle) {
		List<Enchere> encheres = new ArrayList<Enchere>();
		try {
			return this.enchereDAO.getEnchereByArt(noArticle);
		} catch (SQLException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Enchere getEnchereByArtAndUser(int noUtilisateur, int noArticle) {
		Enchere encheres = new Enchere();
		try {
			return this.enchereDAO.getEnchereByArtAndUser(noUtilisateur, noArticle);
		} catch (SQLException | DALException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}
	}

	public List<Enchere> getMesEncheresWin(int noUtilisateur) {
		try {
			return this.enchereDAO.getMesEncheresWin(noUtilisateur);
		} catch (SQLException | DALException e) {

			e.printStackTrace();

		}
		return null;

	}

	public void addEnchere(Enchere enchere) {
		try {
			this.enchereDAO.addEnchere(enchere);
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void deleteEnchere(int noArticle) {
		try {
			this.enchereDAO.deleteEnchere(noArticle);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void setEnchere(Enchere enchere) {
		try {
			this.enchereDAO.setEnchere(enchere);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
