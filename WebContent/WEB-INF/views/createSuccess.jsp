<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/style.css" var="styleCSS" />
<link href="${styleCSS}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${styleCSS}" rel="stylesheet" />
<title>Kino</title>
<link rel="shortcut icon" href="resources/img/icon.png"/>
</head>

<body>
	<div id="page">
		<header>
			<h1 id="logo">
				<img src="resources/img/logo.png" alt="Kino Katowice">
			</h1>

			<nav class="clear">
				<ul>
					<li><a href='/KinoWebApp/'>Strona główna</a></li>
					<li><a href='welcome'>Repertuar</a></li>
					<li><a href='price_list?index=0'>Cennik</a></li>
					<li><a href='login'>Zaloguj</a></li>
				</ul>
			</nav>
		</header>


		<div id="content" role="main">
			<section class="primary">
				<section class="tile_wide">
				<h2>Rejestracja</h2>
					Witaj <strong>${message}</strong><br>
					Rejestracja zakończona powodzeniem!<br>
					Teraz możesz się zalogować <a href='login'>tutaj</a>
				</section>
			</section>
		</div>

		<footer class="clear">
			<section class="footer">
				<p>Copyright © 2017 | Grzmil Paweł</p>
			</section>
		</footer>
	</div>

</body>

</html>
