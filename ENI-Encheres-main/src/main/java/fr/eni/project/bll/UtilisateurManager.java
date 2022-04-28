package fr.eni.project.bll;

import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.DALException;
import fr.eni.project.dal.dao.ArticleVenduDAO;
import fr.eni.project.dal.dao.DAOFactory;
import fr.eni.project.dal.dao.EnchereDAO;
import fr.eni.project.dal.dao.UtilisateurDAO;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
	private ArticleVenduDAO articlevenduDAO = DAOFactory.getArticleVenduDAO();
	private EnchereDAO enchereDAO = DAOFactory.getEnchereDAO();

	public void addUtilisateur(Utilisateur utilisateur) {

		try {

			this.utilisateurDAO.addUtilisateur(utilisateur);

		} catch (DALException e) {

			e.printStackTrace();
		}

	}

	public void upgradeUtilisateur(Utilisateur utilisateur, String pseudo, String mot_de_passe) {

		try {
			// connection avec pseudo / MDP originaux et recuperation du n° pour l'update
			UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
			Utilisateur utilisateurSecure = utilisateurDAO.connecterUtilisateur(pseudo, mot_de_passe);
			int NoUtilisateur = utilisateurSecure.getNoUtilisateur();
			utilisateur.setNoUtilisateur(NoUtilisateur);
			DAOFactory.getUtilisateurDAO().updateUtilisateur(utilisateur);

		} catch (DALException e) {

			e.printStackTrace();
		}

	}

	public void DeleteToArchive(int no_utilisateur) {

		try {
			// changement du n°Utilisateur pour encheres et articles
			enchereDAO.enchereToArchive(no_utilisateur);
			articlevenduDAO.articleToArchive(no_utilisateur);
			// suppression de l'utilisateur
			utilisateurDAO.deleteUtilisateur(no_utilisateur);

		} catch (DALException e) {

			e.printStackTrace();
		}

	}

	public Utilisateur getUtilisateurByID(int noProfil) {
		Utilisateur profil = null;
		try {

			profil = utilisateurDAO.getUtilisateurByID(noProfil);

		} catch (DALException e) {

			e.printStackTrace();
		}
		return profil;
	}

	public Utilisateur connecterUtilisateur(String pseudoORemail, String mot_de_passe) {
		Utilisateur utilisateur = null;
		try {

			utilisateur = this.utilisateurDAO.connecterUtilisateur(pseudoORemail, mot_de_passe);

		} catch (DALException e) {

			e.printStackTrace();
		}
		return utilisateur;
	}

}