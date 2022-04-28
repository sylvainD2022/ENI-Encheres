<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
</head>
<body>
<header>
		<!-- include "standard" : recopie du code html "statique" -->
		<%@ include file="./include/nav.jsp"%>
	</header>
<img src="#" alt="#" />
<form action="./vendreArticle" method="post">
	<label>Article : </label><input type="text" name="nomArticle" value="${article.getNomArticle()}"/><br />
	<label>Description : </label><textarea cols="10" rows="10" name="description" value="${article.getDescription()}"></textarea><br />
	<label>Catégorie : </label><select name="categorie">
<option value="">--Choisissez une option--</option>
		<c:forEach var="categorie" items="${listeCategorie}">
			<option value="${categorie.noCategorie}">${categorie.libelle}</option>
		</c:forEach>
	</select><br />
	<label>Photo de l'article : </label><a href="#">Uploader</a><br />
	<label>Mise à prix : </label><input type="number" name="prix_initial" value="${article.getMiseAPrix()}"/><br />
	<label>Debut de l'enchère : </label><input type="datetime-local" name="debutEnchere" value="${article.getDateDebutEnchere()}"/><br />
	<label>Fin de l'enchère : </label><input type="datetime-local" name="finEnchere" value="${article.getDateFinEnchere()}"/><br /><br />
	
	<!-- affichage du retrait --> 
	<p>Retrait</p><br />
	<label>Rue : </label><input type="text" name="rue" value="${utilisateur.getRue()}" placeholder="${utilisateur.getRue() }"/><br />
	<label>Code postal : </label><input type="text" name="cp" value="${utilisateur.getCode_postal()}" placeholder="${utilisateur.getCode_postal()}"/><br />
	<label>Ville : </label><input type="text" name="ville" value="${utilisateur.getVille()}" placeholder="${utilisateur.getVille()}"/><br />
	<button name="valideCreationVente" value="ok">Enregistrer</button> <button name="valideCreationVente" value ="pasOk">Annuler</button>
	
</form>

</body>
</html>