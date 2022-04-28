package fr.eni.project.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.project.bo.ArticleVendu;
import fr.eni.project.bo.Retrait;
import fr.eni.project.dal.ConnectionProvider;
import fr.eni.project.dal.DALException;
import fr.eni.project.dal.dao.ArticleVenduDAO;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	/** requetes SQL à ajouter */
	private final static String SELECT_ARTICLES = "select * from articles_vendus;";
	private final static String SELECT_ONE_BY_NUMERO_ARTICLE = "select * from articles_vendus where no_article = '?';";
	private final static String INSERT_ARTICLE = "insert into Articles_Vendus(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial) values(?,?,?,?,?);";
	private final static String UPDATE_PRIX_VENTE = "update articles_vendus set prix_vente = ? where no_article='?';";
	private final static String UPDATE_ARTICLES_VENDUS = "update articles_vendus set nom_article = '?',description ='?',date_debut_encheres='?',date_fin_encheres='?',prix_initial='?',prix_vente = '?' where no_article='?';";
	private final static String DELETE_ARTICLE = "delete from articles_vendus where no_article='?';";

	private static final String SELECT_RETRAIT = "select * from retraits where no_article = '?';";
	private static final String INSERT_RETRAIT = "insert into retraits(no_article, rue, code_postal, ville) values('?','?','?','?');";
	private static final String UPDATE_RETRAIT = "update retraits set rue ='?', code_postal ='?', ville='?' where no_article = '?';";
	private static final String DELETE_RETRAIT = "delete * from retraits where no_article = '?';";

	private static final String ARCHIVAGE_ID = "update articles_vendus set no_utilisateur=? where no_utilisateur=?;";

	/**
	 * récupère un article en vente grâce à son noArticle
	 * 
	 * @throws SQLException
	 **/
	public List<ArticleVendu> getAll() throws SQLException {
		List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();

		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		Statement stmt = cnx.createStatement();

		ResultSet rs = stmt.executeQuery(SELECT_ARTICLES);

		while (rs.next()) {
			ArticleVendu article = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
					rs.getTimestamp("date_debut_encheres").toLocalDateTime(),
					rs.getTimestamp("date_fin_encheres").toLocalDateTime(), rs.getInt("prix_initial"),
					rs.getInt("prix_vente"));
			articlesVendus.add(article);
			cnx.close();
		}

		return articlesVendus;
	}

	public ArticleVendu getArticleVendu(int noArticle) throws SQLException {
		ArticleVendu article;

		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ONE_BY_NUMERO_ARTICLE, Statement.RETURN_GENERATED_KEYS);
		pStmt.setInt(1, noArticle);
		ResultSet rs = pStmt.executeQuery(SELECT_ONE_BY_NUMERO_ARTICLE);

		article = new ArticleVendu(rs.getString("nom_article"), rs.getString("description"),
				rs.getTimestamp("date_debut_encheres").toLocalDateTime(),
				rs.getTimestamp("date_fin_encheres").toLocalDateTime(), rs.getInt("prix_initial"),
				rs.getInt("prix_vente"));

		cnx.close();

		return article;
	}

	public void addArticleVendu(ArticleVendu article) throws SQLException {

		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
		// Je remplace les ? par leurs valeurs respectives
		pStmt.setString(1, article.getNomArticle());
		pStmt.setString(2, article.getDescription());
		pStmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
		pStmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
		pStmt.setInt(5, article.getMiseAPrix());

		pStmt.executeUpdate();

		cnx.close();

	}

	public void setArticle(ArticleVendu article) throws SQLException {
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ARTICLES_VENDUS, Statement.RETURN_GENERATED_KEYS);

		pStmt.setString(1, article.getNomArticle());
		pStmt.setString(2, article.getDescription());
		pStmt.setTimestamp(3, Timestamp.valueOf(article.getDateDebutEncheres()));
		pStmt.setTimestamp(4, Timestamp.valueOf(article.getDateFinEncheres()));
		pStmt.setInt(5, article.getMiseAPrix());

		pStmt.setInt(6, article.getNoArticle());// WHERE NoArticle = ?

		pStmt.executeUpdate();

		cnx.close();

	}

	/**
	 * modifie en bdd le prix de vente aprés une enchère
	 * 
	 * @param article
	 * @throws SQLException
	 */
	public void setPrix_vente(ArticleVendu article) throws SQLException {
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(UPDATE_PRIX_VENTE, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, article.getPrixVente());
		pStmt.setInt(2, article.getNoArticle());

		pStmt.executeUpdate();

		cnx.close();

	}

	/**
	 * supprime dans la bdd un article
	 * 
	 * @throws SQLException
	 * 
	 */
	public void deleteArticle(int noArticle) throws SQLException {
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(DELETE_ARTICLE, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, noArticle);

		pStmt.executeUpdate();

		cnx.close();

	}

	/**
	 * method sur la table retraits
	 * 
	 * @throws SQLException
	 */
	public Retrait getRetrait(int noArticle) throws SQLException {
		Retrait retrait = null;
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_RETRAIT, Statement.RETURN_GENERATED_KEYS);
		pStmt.setInt(1, noArticle);
		ResultSet rs = pStmt.executeQuery(SELECT_RETRAIT);
		ArticleVenduDAO articleDAO = null;

		ArticleVendu article = (ArticleVendu) (articleDAO.getArticleVendu(noArticle));
		retrait = new Retrait(article, rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));

		cnx.close();

		return retrait;

	}

	public void addRetrait(Retrait retrait) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(INSERT_RETRAIT, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, retrait.getArticleARetirer().getNoArticle());
		pStmt.setString(2, retrait.getRue());
		pStmt.setString(3, retrait.getCode_postal());
		pStmt.setString(4, retrait.getVille());

		pStmt.executeUpdate();

		cnx.close();
	}

	public void setRetrait(Retrait retrait) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		PreparedStatement pStmt;

		pStmt = cnx.prepareStatement(UPDATE_RETRAIT, Statement.RETURN_GENERATED_KEYS);

		pStmt.setString(1, retrait.getRue());
		pStmt.setString(2, retrait.getCode_postal());
		pStmt.setString(3, retrait.getVille());
		pStmt.setInt(4, retrait.getArticleARetirer().getNoArticle());

		pStmt.executeUpdate();

		cnx.close();
	}

	public void deleteRetrait(int noArticle) throws SQLException {
		Connection cnx;

		cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(DELETE_RETRAIT, Statement.RETURN_GENERATED_KEYS);

		pStmt.setInt(1, noArticle);

		pStmt.executeUpdate();

		cnx.close();

	}

	@Override
	public void articleToArchive(int no_utilisateur) throws DALException {
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
