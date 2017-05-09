<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>

<head>


<spring:url value="/resources/style.css" var="styleCSS" />
<link href="${styleCSS}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${styleCSS}" rel="stylesheet" />
<title>Kino - panel administratora</title>
</head>

<body>
	<div id="page">
		<header>
			<h1 id="logo">
				Panel administratora
			</h1>

			<nav class="clear">
				<ul>
					<li><a href='admin_movies.html'>Filmy</a></li>
					<li><a href='admin_seances.html'>Seanse</a></li>
					<li><a href='admin_users.html'>Użytkownicy</a></li>
					<li><a href='/KinoWebApp/'>Strona główna</a></li>
				</ul>
			</nav>
		</header>


		<div id="content" role="main">
			<section class="primary">
			
				<section class="tile">
					Treść
				</section>
			</section>
		</div>
	</div>
</body>
</html>
