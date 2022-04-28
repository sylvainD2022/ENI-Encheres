package fr.eni.project.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.ConnectionProvider;
import fr.eni.project.dal.DALException;
import fr.eni.project.dal.dao.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?;";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?, credit=? WHERE no_utilisateur=?;";
	private static final String GET_UTILISATEUR_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur from utilisateurs where no_utilisateur = ?;";
	private static final String SELECT_UTILISATEUR = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE (pseudo=? OR email=?) AND mot_de_passe=?";

	public void addUtilisateur(Utilisateur utilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();

			PreparedStatement pstt = cnx.prepareStatement(INSERT_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS)) {

			pstt.setString(1, utilisateur.getPseudo());
			pstt.setString(2, utilisateur.getNom());
			pstt.setString(3, utilisateur.getPrenom());
			pstt.setString(4, utilisateur.getEmail());
			pstt.setString(5, utilisateur.getTelephone());
			pstt.setString(6, utilisateur.getRue());
			pstt.setString(7, utilisateur.getCode_postal());
			pstt.setString(8, utilisateur.getVille());
			pstt.setString(9, utilisateur.getMot_de_passe());
			pstt.setInt(10, utilisateur.getCredit());
			pstt.setBoolean(11, utilisateur.isAdministrateur());

			pstt.executeUpdate();
			ResultSet rs = pstt.getGeneratedKeys();

			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			rs.close();
			cnx.commit();
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DALException("probleme ajout utilisateur");
		}
	}

	public void updateUtilisateur(Utilisateur utilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
			
			PreparedStatement pstt = cnx.prepareStatement(UPDATE_UTILISATEUR)) {
			
			pstt.setString(1, utilisateur.getPseudo());
			pstt.setString(2, utilisateur.getNom());
			pstt.setString(3, utilisateur.getPrenom());
			pstt.setString(4, utilisateur.getEmail());
			pstt.setString(5, utilisateur.getTelephone());
			pstt.setString(6, utilisateur.getRue());
			pstt.setString(7, utilisateur.getCode_postal());
			pstt.setString(8, utilisateur.getVille());
			pstt.setString(9, utilisateur.getMot_de_passe());
			pstt.setInt(10, utilisateur.getCredit());
			pstt.setInt(11, utilisateur.getNoUtilisateur());

			pstt.executeUpdate();
			cnx.commit();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DALException("probleme modification utilisateur");
		}
	}

	public void deleteUtilisateur(int no_utilisateur) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_UTILISATEUR)) {
			pstmt.setInt(1, no_utilisateur);
			pstmt.executeUpdate();
		
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DALException("probleme suppression utilisateur");
		}
	}

	public Utilisateur getUtilisateurByID(int noProfil) throws DALException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				
			PreparedStatement psmt = cnx.prepareStatement(GET_UTILISATEUR_BY_ID);) {
			
			psmt.setInt(1, noProfil);
			ResultSet rs = psmt.executeQuery();

			while (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			}

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DALException("probleme recuperation utilisateur");
		}

		return utilisateur;
	}

	public Utilisateur connecterUtilisateur(String pseudoORemail, String mot_de_passe) throws DALException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				
			PreparedStatement pstt = cnx.prepareStatement(SELECT_UTILISATEUR)) {
			
			pstt.setString(1, pseudoORemail);
			pstt.setString(2, pseudoORemail);
			pstt.setString(3, mot_de_passe);
			ResultSet rs = pstt.executeQuery();
			
			while (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdministrateur(rs.getBoolean("administrateur"));

			}
			if (utilisateur.getNoUtilisateur() == 0) {
				throw new DALException("utilisateur inconnu ou mot de passe incorrect");
			}
			rs.close();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DALException("probleme connexion");

		}

		return utilisateur;
	}
}
