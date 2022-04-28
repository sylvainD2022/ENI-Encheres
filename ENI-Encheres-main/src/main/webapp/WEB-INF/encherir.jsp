<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enchérir</title>
</head>
<body>
  <header class="row">
		<%@ include file="./include/nav.jsp"%>
	</header>
		<h1>Détails Vente</h1>
		<br>
		<img src="#" alt="#" />
	
		<label>${article.getNomArticle()}</label> <br>
		<label>Description : </label>
		${description } <br>
		<br>
		<label>Catégorie : </label>
		${article.getCategorie().getLibelle()}<br>
		<br>
		<label>meilleure offre : </label>
		${article.getPrixVente()} <br>
		<br>
		<label>Mise à prix : </label>
		${article.getMiseAPrix()} <br><br>
		<label>Fin de l'enchère :</label>
		${article.getDateFinEncheres() }><br>
		<label>Retrait : br<br>
		${retrait.getRue()}<br>
		${retrait.getCode_postal()} ${retrait.getVille()}  <br><br>
		<label> Vendeur : </label>${article.proprio.pseudo}<br>
		<form action="./enchereRetourAccueilServlet" method = "post" >
			<label>Ma Proposition : </label><input name="proposition" type="number"  min="${enchere.getMontantEnchere()}+5" max="${utilisateur.getCredit() }" step="5">
									<button name="articleConcerne" value"${article.prixVente}">Enchérir</button>	
							   </form>
		
								

</body>
</html>