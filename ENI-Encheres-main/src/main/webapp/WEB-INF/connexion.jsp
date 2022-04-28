<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

</head>
<body>
	<form action="./connexion" method="post">
		<label>Identifiant : </label><input type="text" id="identifiant"
			name="identifiant" required /><br /> <label>Mot de passe : </label><input
			type="password" id="MDP" name="MDP" required /><br /> <input
			type="submit" value="Se connecter"> <input type="checkbox" /><label>Se
			souvenir de moi</label> <a href="#">Mot de passe oublié</a>

	</form>
	<form action="creerCompte">
		<button>Créer un compte</button>
	</form>

</body>
</html>