package fr.eni.project.dal.dao;

import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.DALException;

public interface UtilisateurDAO {
	public void addUtilisateur(Utilisateur utilisateur) throws DALException;

	public void updateUtilisateur(Utilisateur utilisateur) throws DALException;

	public void deleteUtilisateur(int noProfil) throws DALException;

	public Utilisateur getUtilisateurByID(int no_utilisateur) throws DALException;

	public Utilisateur connecterUtilisateur(String pseudoORemail, String mot_de_passe) throws DALException;
}