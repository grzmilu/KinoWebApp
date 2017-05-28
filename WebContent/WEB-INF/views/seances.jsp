<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>





<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/style.css" var="styleCSS" />
<link href="${styleCSS}" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${styleCSS}" rel="stylesheet" />
<title>Kino</title>
<link rel="shortcut icon" href="resources/img/icon.png" />
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
					<li><a class="active" href='welcome'>Repertuar</a></li>
					<li><a href='price_list?index=0'>Cennik</a></li>
					<sec:authorize access="hasRole('ANONYMOUS')">
						<li><a href='anon_booking'>Rezerwacje</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href='admin/admin_panel'>Panel Admimistratora</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ANONYMOUS')">
						<li><a href='login'>Zaloguj</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
						<li><a href="<c:url value="/logout" />">Wyloguj</a></li>
					</sec:authorize>
				</ul>
			</nav>
		</header>


		<div id="content" role="main">
			<section class="primary">
			<section class="tile">
				<a class="prices" href="seances">Seanse</a>|<a class="prices" href="welcome">Filmy</a>
			</section>
			<section class="tile">
			<h1>Wybierz datę:</h1>
			<p>
				<c:forEach items="${days}" var="day">
					<a class="prices" href="seances?date=${day}">${day}</a>|
				</c:forEach>
			</p>
			</section>
			<c:forEach items="${seances}" var="seance">
			<a href='seance_detail?id=${seance.ID}'>
					<section class="tile_list">
						<h2>${seance.title}</h2>
						<p>${seance.startTime}</p>
					</section>
					</a>
				</c:forEach>
			</section>

			<aside class="secondary">
				<section>
					<sec:authorize access="hasAnyRole('ADMIN','USER')">
						<p>
							Witaj <strong>${user.name}</strong>
						</p>
						<p>
							<a href="user/user_show_profile">Zobacz swój profil</a>
						</p>
					</sec:authorize>
				</section>
				</aside>
				<aside class="secondary">
				<h2>W najbliższym czasie:</h2>
				<c:forEach items="${future_seances}" var="f_seance">
					<section>
						<hr>
						<h2>
							<a href='movie_detail?title=${f_seance.title}'>${f_seance.title}</a>
						</h2>
						<ul>
							<li><a href='seance_detail?id=${f_seance.ID}'>${f_seance.startTime}</a></li>
						</ul>
					</section>
				</c:forEach>
			</aside>
		</div>

		<footer class="clear">
			<section class="footer">
				<p>Copyright © 2017 | Grzmil Paweł</p>
			</section>
		</footer>
	</div>

</body>

</html>
