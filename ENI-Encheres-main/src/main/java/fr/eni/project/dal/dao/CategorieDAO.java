package fr.eni.project.dal.dao;

import java.sql.SQLException;
import java.util.List;

import fr.eni.project.bo.Categorie;

public interface CategorieDAO {
	public List<Categorie> getAll() throws SQLException;

	public Categorie getOne(int id) throws SQLException;
}
