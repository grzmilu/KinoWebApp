<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<li><a href='welcome'>Repertuar</a></li>
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
					<h2>Dane seansu</h2>
					<p>
						Data seansu: <span>${date}</span>
					</p>
					<p>
						Godzina rozpoczęscia seansu: <span>${time}</span>
					</p>
					<p>
						<a class="prices" href="booking_select?seance=${seance.ID}">REZERWUJ/KUP BILET</a>
					</p>
					<img src="resources/img/${movie.poster}.jpg" alt="${movie.title}">
					<h2>${movie.title}</h2>
					<p>
						Gatunek: <span>${movie.genre}</span>
					</p>
					<p>
						Reżyseria: <span>${movie.direction}</span>
					</p>
					<p>
						Scenariusz: <span>${movie.scenario}</span>
					</p>
					<p>
						Czas trwania: <span>${movie.duration} min.</span>
					</p>
					<p>
						Od lat: <span>${movie.pegi}</span>
					</p>
					<p>
						Premiera: <span>${movie.releaseYear}</span>
					</p>
					<p>
						Opis filmu:<br> <span>${movie.description}</span>
					</p>
					<h2>Zwiastun:</h2>
					<iframe id="ytplayer" type="text/html" width="683" height="512"
						src="https://www.youtube.com/v/${movie.trailer}?disablekb=1&fs=0&rel=0&showinfo=0&autohide=1&iv_load_policy=3&vq=large"
						frameborder="0"></iframe>
					
				</section>
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
		</div>

		<footer class="clear">
			<section class="footer">
				<p>Copyright © 2016 | Grupa C</p>
			</section>
		</footer>
	</div>

</body>

</html>
