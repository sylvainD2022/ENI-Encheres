package fr.eni.project.bll;

import java.sql.SQLException;
import java.util.List;

import fr.eni.project.bo.Categorie;
import fr.eni.project.dal.dao.CategorieDAO;
import fr.eni.project.dal.dao.DAOFactory;

public class CategorieManager {

	private CategorieDAO categorieDAO = DAOFactory.getCategorieDAO();

	public List<Categorie> getAll() {
		try {
			return this.categorieDAO.getAll();
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	public Categorie getOne(int noCategorie) {
		try {
			return this.categorieDAO.getOne(noCategorie);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}
}
