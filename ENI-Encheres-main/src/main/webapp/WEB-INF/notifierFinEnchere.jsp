<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fin d'enchère</title>
</head>
<body>
	<img src="#" alt="#" />
	<label>${article}.nom</label>
	<br />
	<label>Descrition : </label>${article}.description<br />
	<label>Meilleure offre : </label>
	<label>${article}.prixVente <span>par </span>bidule
	</label>
	<br />
	<!--  bidule doit devenir enchere.acheteur.pseudo -->
	<label>Mise a prix : </label>
	<label>${article}.miseAPrix</label>
	<br />
	<label>Fin de l'enchère : </label>
	<label>${article}.dateFinEnchere</label>
	<br />
	<label>Retrait : </label>
	<label>${retrait}.rue ${retrait}.code_Postal ${retrait}.ville</label>
	<br />
	<label>Vendeur : </label>
	<label>${article}.proprio</label>
	<br />
	<form action="./deleteEnchereServlet" method="post">
		<button name="delete" value="${article}.noArticle">Retrait
			effectué</button>
	</form>

</body>
</html>