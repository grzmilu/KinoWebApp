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
					<li><a class="active" href='/KinoWebApp/'>Strona główna</a></li>
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
					<h1>Nowość:</h1>
					<h2>
						<a href='movie_detail?title=${latest.title}'>${latest.title}</a>
					</h2>
						<div style="width: 683px; height: 512px; float: none; clear: both; margin: 2px auto;">
  							<embed src="https://www.youtube.com/v/${latest.trailer}?disablekb=1&fs=0&rel=0&showinfo=0&autohide=1&iv_load_policy=3&vq=large" wmode="transparent" type="application/x-shockwave-flash" width="100%" height="100%" allowfullscreen="true" title="Adobe Flash Player">
						</div>
					<p>${latest.description}</p>
				</section>
				<section class="tile">
					<h2>Polecamy:</h2>
					<section class="poster">
						<a href='movie_detail?title=Commando'><img
							src="resources/img/p0.jpg" alt="Tytuł filmu 1"></a>
					</section>
					<section class="poster">
						<a href='movie_detail?title=Annabel'><img
							src="resources/img/p1.jpg" alt="Tytuł filmu 2"></a>
					</section>
					<section class="poster">
						<a href='movie_detail?title=Obecność'><img
							src="resources/img/p2.jpg" alt="Tytuł filmu 3"></a>
					</section>
					<section class="poster">
						<a href='movie_detail?title=Piła 7'><img
							src="resources/img/p3.jpg" alt="Tytuł filmu 4"></a>
					</section>
				</section>
			</section>

			<aside class="secondary">
				<section>


					<sec:authorize access="hasAnyRole('ADMIN','USER')">

						<p>
							Witaj <strong>${user.name}</strong>
						</p>
						<p><a href="user/user_show_profile">Zobacz swój profil</a></p>
					</sec:authorize>
				</section>

			</aside>


			<aside class="secondary">
				<h2>W najbliższym czasie:</h2>
				<c:forEach items="${seances}" var="seance">
					<section>
						<hr>
						<h2>
							<a href='movie_detail?title=${seance.title}'>${seance.title}</a>
						</h2>
						<ul>
							<li><a href='seance_detail?id=${seance.ID}'>${seance.startTime}</a></li>
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
