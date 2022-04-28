<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier profil</title>
</head>
<body>
	<header class="row">
		<%@ include file="./include/nav.jsp"%>
	</header>
	<h1>Mon profil</h1>
	<form action="./modifierProfil" method="post">
		<input type="text" id="pseudo" name="pseudo" value="${utilisateur.getPseudo()}" hidden>
		<input type="text" id="NoUtilisateur" name="NoUtilisateur" value="${utilisateur.getNoUtilisateur()}" hidden>	
		<label>Pseudo : </label><input type="text" id="pseudo" name="newPseudo" value="${utilisateur.getPseudo()}"/><br/>
		<label>Nom : </label><input type="text" id="nom" name="nom" value="${utilisateur.getNom()}"/><br/>
		<label>Prenom : </label><input type="text" id="prenom" name="prenom" value="${utilisateur.getPrenom()}"/><br/>
		<label>Email : </label><input type="email" id="email" name="email" value="${utilisateur.getEmail()}"/><br/>
		<label>Telephone : </label><input type="tel" id="tel" name="tel" value="${utilisateur.getTelephone()}"/><br/>
		<label>Rue : </label><input type="text" id="rue" name="rue" value="${utilisateur.getRue()}"/><br/>
		<label>Code postal :</label><input type="text" id="codepostal" name="codepostal" value="${utilisateur.getCode_postal()}" /><br/>
		<label>Ville : </label><input type="text" id="ville" name="ville" value="${utilisateur.getVille()}"/><br/>
		<label>Mot de passe actuel : </label><input type="password" id="mot_de_passe" name="mot_de_passe" /><br/>
		<label>Nouveau mot de passe : </label><input type="password" id="newMDP" name="newMDP"/><br/>
		<label>Confirmer : </label><input type="password" id="confirm" name="confirm"/><br/>
		<input type="submit" name="tache" value="Enregistrer">
        <input type="submit" name="tache" value="Supprimer le compte">
	</form>

</body>
</html>