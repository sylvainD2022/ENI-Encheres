package fr.eni.project.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.project.dal.dao.ArticleVenduDAO;

/**
 * 
 * @author sdubuisson2022
 *
 */
public class Retrait {
	private String rue;
	private String code_postal;
	private String ville;
	private ArticleVendu articleARetirer = new ArticleVendu();

	public Retrait(ArticleVendu article, String rue, String code_postal, String ville1) {
		super();
		this.articleARetirer = article;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville1;

	}

	public Retrait(ArticleVendu article) {
		super();
		this.articleARetirer = article;
		this.rue = article.getProprio().getRue();
		this.code_postal = article.getProprio().getCode_postal();
		this.ville = article.getProprio().getVille();

	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public ArticleVendu getArticleARetirer() {
		return articleARetirer;
	}

	public void setArticleARetirer(ArticleVendu articleARetirer) {
		this.articleARetirer = articleARetirer;
	}

	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", articlesARetirer="
				+ articleARetirer + "]";
	}

}
