package fr.eni.project.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import fr.eni.project.bll.ArticleVenduManager;

/**
 * 
 * @author sdubuisson2022
 *
 */
public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix; // en BDD : prix_initial
	private int prixVente; // en BDD : prix_vente
	private String etatVente;
	private Utilisateur proprio;
	private Retrait lieuRetrait;
	private Categorie categorie;

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Utilisateur proprio,
			Retrait lieuRetrait, Categorie categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.proprio = proprio;
		this.lieuRetrait = lieuRetrait;
		this.categorie = categorie;
	}

	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente, Utilisateur proprio,
			Retrait lieuRetrait, Categorie categorie) {
		super();

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.proprio = proprio;
		this.lieuRetrait = lieuRetrait;
		this.categorie = categorie;
	}

	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente) {
		super();

		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;

	}

	public ArticleVendu() {
		super();

	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getProprio() {
		return proprio;
	}

	public void setProprio(Utilisateur proprio) {
		this.proprio = proprio;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	/**
	 * 
	 * @description etatVente 3 cas si la date de début n'est pas dépassée alors
	 *              non débutée si la date de fin est dépassée alors terminée
	 *              sinon en cours
	 */
	public void addEtatVente(ArticleVendu article) {
		LocalDateTime venteDateDebut = article.getDateDebutEncheres();
		LocalDateTime venteDateFin = article.getDateFinEncheres();

		if (venteDateDebut.isAfter(LocalDateTime.now())) {
			this.setEtatVente("Non débutée");
		} else if (venteDateFin.isBefore(LocalDateTime.now())) {
			this.setEtatVente("Terminée");
		} else {
			this.setEtatVente("En Cours");
		}
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", proprio=" + proprio
				+ ", lieuRetrait=" + lieuRetrait + ", categorie=" + categorie + "]";
	}

}
