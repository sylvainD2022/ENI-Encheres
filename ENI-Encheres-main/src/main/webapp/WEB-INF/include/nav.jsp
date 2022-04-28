<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="./imports.html"%>
<%@ page import="fr.eni.project.bo.Utilisateur"%>

<% Boolean utilisateurConnecte = (Boolean) request.getSession().getAttribute("utilisateurConnecte");%>
<% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");%>

<nav>

	<a href="./"><img src="images/logo.png"
		class="img-fluid img-rounded" />ENI-Encheres</a>
	<nav class="col">
		<ul class="nav justify-content-end">
			<c:choose>

				<c:when test="${utilisateurConnecte}">
					<!-- formulaire de déconnexion -->
					<%-- parce qu'on utilise des sous -url /admin on doit utiliser : ${pageContext.request.contextPath} : réference vers l'url de l'application : http://localhost:8080/DemoSessionFiltre --%>

					<li class="nav-item"><a class="nav-link" href="./">Encheres</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/vendreArticle">Vendre
							un article</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/profil">Mon profil</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/accueil?deconnect=1">Déconnexion</a></li>
		</ul>
	</nav>

	</c:when>

	<c:when test="${!utilisateurConnecte}">
		<%-- parce qu'on utilise des sous -url /admin on doit utiliser : ${pageContext.request.contextPath} : réference vers l'url de l'application : http://localhost:8080/DemoSessionFiltre --%>
		<li class="nav-item"><a class="nav-link"
			href="${pageContext.request.contextPath}/connexion">S'inscrire -
				se connecter</a></li>
		</ul>
</nav>
</c:when>
</c:choose>
</nav>