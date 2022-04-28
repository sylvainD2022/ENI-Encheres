package fr.eni.project.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.project.bll.ArticleVenduManager;
import fr.eni.project.bll.UtilisateurManager;
import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Enchere;
import fr.eni.project.bo.Utilisateur;
import fr.eni.project.dal.ConnectionProvider;
import fr.eni.project.dal.DALException;
import fr.eni.project.dal.dao.ArticleVenduDAO;
import fr.eni.project.dal.dao.EnchereDAO;
import fr.eni.project.dal.dao.UtilisateurDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String SELECT_ENCHERES = "select * from encheres;";
	private static final String SELECT_ENCHERES_BY_ARTICLE = "select * from encheres where no_article='?';";
	private static final String SELECT_ENCHERES_BY_ARTICLE_USER = "select * from encheres where no_article='?' and no_utilisateur = '?';";

	// le TOP n recupére toutes les lignes jusqu'à la ligne n
	private static final String SELECT_LAST_ENCHERE = "SELECT TOP 1 * FROM encheres where no_utilisateur= '?' and no_article = '?' ORDER BY montant_enchere DESC;";

	private static final String INSERT_ENCHERE = "insert into encheres(no_utilisateur,no_article,date_enchere,montant_enchere) values('?','?','?','?');";

	private static final String UPDATE_ENCHERE = "update encheres set date_enchere= '?', montant_enchere = '?' where no_utilisateur ='?' and no_article='?';";

	private static final String DELETE_ENCHERE = "delete from encheres where no_article = '?';";
	private static final String SELECT_ENCHERES_END = "select * from encheres e join articles_vendus a  on e.noArticle = a.noArticle where dateDiff(second,now(),date_fin_enchere);";// toutes
																																														// les
																																														// encheres
																																														// terminées

	private static final String ARCHIVAGE_ID = "update encheres set no_utilisateur=? where no_utilisateur=?;";

	public List<Enchere> getAll(ArticleVendu article, Utilisateur user) throws SQLException {
		List<Enchere> listEncheres = new ArrayList<Enchere>();

		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		Statement stmt = cnx.createStatement();

		ResultSet rs = stmt.executeQuery(SELECT_ENCHERES);

		while (rs.next()) {

			Enchere enchere = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"),
					rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));

			listEncheres.add(enchere);

		}
		cnx.close();
		return listEncheres;

	}

	public List<Enchere> getEnchereByArt(int noArticle) throws SQLException, DALException {

		UtilisateurDAO user = null;
		ArticleVenduDAO article = null;
		List<Enchere> enchereList = new ArrayList<Enchere>();

		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(SELECT_ENCHERES_BY_ARTICLE, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, noArticle);

		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {

			Enchere enchere = new Enchere(user.getUtilisateurByID(rs.getInt("no_Utilisateur")),
					article.getArticleVendu(noArticle), rs.getTimestamp("date_enchere").toLocalDateTime(),
					rs.getInt("montant_enchere"));
			enchereList.add(enchere);
		}
		return enchereList;

	}

	public Enchere getEnchereByArtAndUser(int noUtilisateur, int noArticle) throws SQLException, DALException {
		UtilisateurDAO user = null;
		ArticleVenduDAO article = null;
		Enchere enchere = null;
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(SELECT_ENCHERES_BY_ARTICLE_USER, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, noUtilisateur);
		pStmt.setInt(2, noArticle);
		ResultSet rs = pStmt.executeQuery();

		enchere = new Enchere(user.getUtilisateurByID(noUtilisateur), article.getArticleVendu(noArticle),
				rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));

		return enchere;
	}

	public Enchere getEnchereMax(int noUtilisateur, int noArticle) throws SQLException, DALException {

		UtilisateurDAO user = null;
		ArticleVenduDAO article = null;
		Enchere enchere = null;
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(SELECT_LAST_ENCHERE, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, noUtilisateur);
		pStmt.setInt(2, noArticle);
		ResultSet rs = pStmt.executeQuery();

		enchere = new Enchere(user.getUtilisateurByID(noUtilisateur), article.getArticleVendu(noArticle),
				rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"));

		return enchere;

	}

	public List<Enchere> getMesEncheresWin(int noUtilisateur) throws SQLException, DALException {
		UtilisateurDAO user = null;
		ArticleVenduDAO article = null;

		List<Enchere> encheresFin = new ArrayList<Enchere>();
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		Statement stmt = cnx.createStatement();

		ResultSet rs = stmt.executeQuery(SELECT_ENCHERES_END);
		while (rs.next()) {

			Enchere enchere = new Enchere(user.getUtilisateurByID(rs.getInt("no_Utilisateur")),
					article.getArticleVendu(rs.getInt("no_Article")), rs.getTimestamp("date_enchere").toLocalDateTime(),
					rs.getInt("montant_enchere"));
			if ((enchere.getAcheteur().getNoUtilisateur() == noUtilisateur)
					&& (enchere.getMontant_enchere() == enchere.getArticle().getPrixVente())) {
				encheresFin.add(enchere);
			}
		}

		cnx.close();

		return encheresFin;

	}

	public void addEnchere(Enchere enchere) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt1 = cnx.prepareStatement(SELECT_ENCHERES_BY_ARTICLE_USER,
				Statement.RETURN_GENERATED_KEYS);
		PreparedStatement pStmt2 = cnx.prepareStatement(UPDATE_ENCHERE, Statement.RETURN_GENERATED_KEYS);
		PreparedStatement pStmt3 = cnx.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = pStmt1.executeQuery();
		// si une enchère exite déjà on fait un update
		if (rs.next()) {

			pStmt2.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
			pStmt2.setInt(2, enchere.getMontant_enchere());
			pStmt2.setInt(3, enchere.getAcheteur().getNoUtilisateur());
			pStmt2.setInt(4, enchere.getArticle().getNoArticle());
			pStmt2.executeUpdate();
			// sinon on execute un insert
		} else {
			pStmt3.setInt(1, enchere.getAcheteur().getNoUtilisateur());
			pStmt3.setInt(2, enchere.getArticle().getNoArticle());
			pStmt3.setTimestamp(3, Timestamp.valueOf(enchere.getDateEnchere()));
			pStmt3.setInt(4, enchere.getMontant_enchere());

			pStmt3.executeUpdate();
		}

		cnx.close();

	}

	public void setEnchere(Enchere enchere) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(UPDATE_ENCHERE, Statement.RETURN_GENERATED_KEYS);

		pStmt.setTimestamp(1, Timestamp.valueOf(enchere.getDateEnchere()));
		pStmt.setInt(2, enchere.getMontant_enchere());
		pStmt.setInt(3, enchere.getAcheteur().getNoUtilisateur());
		pStmt.setInt(4, enchere.getArticle().getNoArticle());
		pStmt.executeUpdate();

		cnx.close();

	}

	public void deleteEnchere(int noArticle) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(DELETE_ENCHERE, Statement.RETURN_GENERATED_KEYS);
		pStmt.setInt(1, noArticle);

		pStmt.executeUpdate();

		cnx.close();

	}

	@Override
	public void enchereToArchive(int no_utilisateur) throws DALException {
		// TODO Auto-generated method stub
		final int archiveID = -2;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstt = cnx.prepareStatement(ARCHIVAGE_ID);) {
			pstt.setInt(1, archiveID);
			pstt.setInt(2, no_utilisateur);
			pstt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
