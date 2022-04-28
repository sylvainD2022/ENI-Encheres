package fr.eni.project.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.project.bll.ArticleVenduManager;
import fr.eni.project.bll.UtilisateurManager;
import fr.eni.project.dal.dao.UtilisateurDAO;

/**
 * 
 * @author sdubuisson2022
 *
 */
public class Enchere {
	private LocalDateTime dateEnchere;

	private Utilisateur acheteur;
	private ArticleVendu article;
	private int montant_enchere;

	public Enchere(Utilisateur acheteur, ArticleVendu article, LocalDateTime dateEnchere, int montant_enchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.article = article;
		this.acheteur = acheteur;
		this.montant_enchere = montant_enchere;
	}

	public Enchere(ArticleVendu article, Utilisateur acheteur, int montant_enchere) {
		super();
		this.article = article;
		this.acheteur = acheteur;
		this.montant_enchere = montant_enchere;
	}

	public Enchere() {
		super();

	}

	public Enchere(int noUtilisateur, int No_article, LocalDateTime dateEnchere, int montant_enchere) {

		Utilisateur user = new UtilisateurManager().getUtilisateurByID(noUtilisateur);
		ArticleVendu article = new ArticleVenduManager().getArticleVendu(No_article);
		Enchere enchere = new Enchere(user, article, dateEnchere, montant_enchere);

	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public int getMontant_enchere() {
		return montant_enchere;
	}

	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

}
