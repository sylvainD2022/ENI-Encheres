<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>

</head>
<body>
	<header>
		<!-- include "standard" : recopie du code html "statique" -->
		<%@ include file="./include/nav.jsp"%>
	</header>
	<h1>Liste des encheres</h1>
	<form action="${pageContext.request.contextPath}/accueil" method="get">
		<fieldset class="row">
			<legend>Filtres : </legend>
			<div class="col-6">
				<div class="input-group pb-2">
					<input type="text" name="nomArticle" id="nomArticle"
						class="form-control" placeholder="Le nom de l'article contient"
						value="${nomArticle}">
				</div>
				<div class="form-floating pb-2">
					<select name="ListeCategories" id="floatingSelect"
						class="form-select" aria-label="Floating label select example">
						<option value="">--Choisissez une option--</option>
						<c:forEach var="categorie" items="${listeCategorie}">
							<option value="${categorie.noCategorie}">${categorie.libelle}</option>
						</c:forEach>
						<label class="form-select" for="floatingSelect">Catégorie</label>
					</select>
				</div>
			</div>

			<c:choose>

				<c:when test="${utilisateurConnecte}">
					<!-- if connecté -->
					<div class="col">
						<div>
							<input type="radio" value="achat" name="radio" id="radioAchat"
								onClick="MyFonctionAchat()" />
							<c:if test="${radio == \"achat\"}">checked</c:if>
							> <label for="radioAchat">Achat</label>
							<div>
								<input type="checkbox" value="ouvertes"
									id="CheckBoxEnchereOuverte" name="CheckBoxEnchereOuverte"
									<c:if test="${radio == \"mesVentes\"}">disabled </c:if>
									<c:if test="${CheckBoxEnchereOuverte != null}">checked </c:if>>
								<label for="CheckBoxEnchereOuverte">enchères ouvertes</label>
							</div>
							<div>
								<input type="checkbox" value="En cours"
									id="CheckBoxEnchereEnCours" name="CheckBoxEnchereEnCours"
									<c:if test="${radio == \"mesVentes\"}">disabled </c:if>
									<c:if test="${CheckBoxEnchereEnCours != null}">checked </c:if>>
								<label for="CheckBoxEnchereEnCours">enchères en cours</label>
							</div>
							<div>
								<input type="checkbox" value="remportees"
									id="checkBoxEnchereRemportees" name="checkBoxEnchereRemportes"
									<c:if test="${radio == \"mesVentes\"}">disabled </c:if>
									<c:if test="${checkBoxEnchereRemportes != null}">checked </c:if>>
								<label for="checkBoxEnchereRemportees">enchères
									remportées</label>
							</div>

						</div>

					</div>
					<div class="col">
						<div>
							<input type="radio" value="mesVentes" name="radio"
								id="radioMesVentes" onClick="MyFonctionVentes()" />
							<c:if test="${radio == \"achat\"}">checked</c:if>
							> <label for="radioAchat">Mes Ventes</label>
							<div>
								<input type="checkbox" id="CheckBoxVentesEnCours"
									name="CheckBoxVentesEnCours"
									<c:if test="${radio == \"achat\"}">disabled </c:if>
									<c:if test="${CheckBoxVentesEnCours != null}">checked </c:if>>
								<label for="CheckBoxVentesEnCours">mes ventes en cours</label>
							</div>
							<div>
								<input type="checkbox" value="ventesNonDebutees"
									id="CheckBoxVentesNonDebutees" name="CheckBoxVentesNonDebutees"
									<c:if test="${radio == \"achat\"}">disabled </c:if>
									<c:if test="${CheckeBoxVentesNonDebutees != null}">checked </c:if>>
								<label for="CheckBoxVentesNonDebutees">ventes non
									débutées</label>
							</div>
							<div>
								<input type="checkbox" value="terminees"
									id="CheckBoxVentesTerminees" name="CheckBoxVentesTerminees"
									<c:if test="${radio == \"achat\"}">disabled </c:if>
									<c:if test="${CheckBoxVentesTerminees  != null}">checked </c:if>>
								<label for="CheckBoxVentesTerminees">ventes terminées</label>
							</div>

						</div>
				</c:when>
			</c:choose>
			</div>
			<input class="btn btn-lg btn-primary" type="submit"
				value="Rechercher">
		</fieldset>
	</form>


	<%-- 	<!-- Toutes les encheres en cours à ajouter -->
	<form action="/accueil" method="post">
		<c:forEach items="${articles}" var="articleVendu">
			<button name="nomArticle" value="${articleVendu }">${articleVendu }.nom</button>
			<br>
			<label>Prix de vente: </label>${articleVendu }.prixVente<br>
			<br>
			<label>Fin de l'enchère : </label>${articleVendu }.dateFinEnchere
		<br>
			<br>
			<label>Vendeur : </label>
			<button name="vendeur" value="${articleVendu }.proprio.nom">${articleVendu }.proprio.nom</button>
		</c:forEach>

    </select>
	<button>Rechercher</button>
	<br />
	
<!-- Toutes les encheres en cours à ajouter -->
<form action="/vueEncherirServlet" method="post">
	<c:forEach items="${articles}" var="articleVendu">
		<button name="nomArticle" value="${articleVendu}">${articleVendu.nom}</button><br>
		<label>Prix de vente: </label>${articleVendu.prixVente}<br><br>
		<label>Fin de l'enchère : </label>${articleVendu.dateFinEnchere}
		<br><br>
		<label>Vendeur : </label><button name="vendeur" value="${articleVendu.proprio.nom}">${articleVendu.proprio.nom}</button>
	</c:forEach>
</form>
--%>

	<script>
function myFunctionVentes() {
	this.getElementById("CheckBoxEnchereOuverte").disabled = true;
	this.getElementById("CheckBoxEnchereEnCours").disabled = true;
	this.getElementById("checkBoxEnchereRemportes").disabled = true;
}
function myFunctionAchat() {
	this.getElementById("CheckBoxVentesEnCours").disabled = true;
	this.getElementById("CheckBoxVentesNonDebutees").disabled = true;
	this.getElementById("CheckBoxVentesTerminees").disabled = true;
}
</script>

</body>

</html>