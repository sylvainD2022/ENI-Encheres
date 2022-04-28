<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer un compte</title>

</head>
<body class="container">
	<header class="row">
		<%@ include file="./include/nav.jsp"%>
	</header>
	<h1>Mon profil</h1>
	<form action="creerCompte" method="post">
		<label>Pseudo : </label><input type="text" id="pseudo" name="pseudo"
			value="${pseudo}" placeholder="pseudo" required /><br /> <label>Nom
			: </label><input type="text" id="nom" name="nom" value="${nom}"
			placeholder="Nom" required /><br /> <label>Prenom : </label><input
			type="text" id="prenom" name="prenom" value="${prenom}"
			placeholder="Prénom" required /><br /> <label>Email : </label><input
			type="email" id="email" name="email" value="${email}"
			placeholder="monmail@mail.com" required /><br /> <label>Téléphone
			: </label><input type="tel" id="tel" value="${tel}" name="tel" /><br /> <label>Rue
			: </label><input type="text" id="rue" name="rue" value="${rue}"
			placeholder="n° rue marue" required /><br /> <label>Code
			postal :</label><input type="text" id="codepostal" name="codepostal"
			value="${codepostal}" placeholder="00000" required /><br /> <label>Ville
			: </label><input type="text" id="ville" name="ville" value="${ville}"
			placeholder="Ma Ville" required /><br /> <label>Mot de passe
			: </label><input type="password" id="mot_de_passe" name="mot_de_passe"
			required /><br /> <label>Confirmation : </label><input
			type="password" id="confirm" name="confirm" required /><br /> <input
			type="submit" value="Créer">
		<button id=" retourAccueil">Annuler</button>
	</form>


</body>
</html>