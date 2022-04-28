package fr.eni.project.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.project.dal.ConnectionProvider;
import fr.eni.project.bo.Categorie;
import fr.eni.project.dal.dao.CategorieDAO;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private final static String SELECT_CATEGORIES = "select * from Categories;";
	private final static String SELECT_ONE = "select * from Categories where no_categorie = ?;";

	@Override
	public List<Categorie> getAll() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_CATEGORIES);
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		while (rs.next()) {
			Categorie categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
			listeCategorie.add(categorie);
		}
		return listeCategorie;
	}

	@Override
	public Categorie getOne(int id) throws SQLException {
		Categorie categorie = null;
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement stmt = cnx.prepareStatement(SELECT_ONE);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
		}

		return categorie;
	}

}
