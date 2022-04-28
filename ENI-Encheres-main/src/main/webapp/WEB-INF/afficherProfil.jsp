<%@ page import="fr.eni.project.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>

</head>
<body>
	<header class="row">
		<%@ include file="./include/nav.jsp"%>
	</header>

	<label>Pseudo : ${utilisateur.pseudo}</label>
	<br />
	<label>Nom : ${utilisateur.nom} </label>
	<br />
	<label>Prenom : ${utilisateur.prenom} </label>
	<br />
	<label>E-mail : ${utilisateur.email} </label>
	<br />
	<label>Téléphone : ${utilisateur.telephone}</label>
	<br />
	<label>Rue : ${utilisateur.rue} </label>
	<br />
	<label>Code postal : ${utilisateur.code_postal} </label>
	<br />
	<label>Ville : ${utilisateur.ville}</label>
	<br />

	<c:if test="${utilisateur.noUtilisateur == utilisateur.noUtilisateur}">
		<a href="${pageContext.request.contextPath}/modifierProfil"
			id="modifierProfil">Modifier</a>
	</c:if>





</body>
</html>