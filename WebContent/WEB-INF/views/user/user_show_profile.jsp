<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>

<spring:url value="/resources/style.css" var="styleCSS" />
<link href="${styleCSS}" rel="stylesheet" />
<title>Kino</title>

<link rel="shortcut icon" href="../resources/img/icon.png" />
</head>

<body>
	<div id="page">
		<header>
			<h1 id="logo">
				<img src="../resources/img/logo.png" alt="Kino Katowice">
			</h1>

			<nav class="clear">
				<ul>
					<li><a href='/KinoWebApp/'>Strona główna</a></li>
					<li><a href='../welcome'>Repertuar</a></li>
					<li><a href='../price_list?index=0'>Cennik</a></li>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a href='../admin/admin_panel'>Panel Admimistratora</a></li>
					</sec:authorize>
					<sec:authorize access="hasRole('ANONYMOUS')">
						<li><a href='../login'>Zaloguj</a></li>
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
					<h2>Dane:</h2>
					<form action="user_show_profile.html" method="POST">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table>
							<tr>
								<td>Login: ${user.login}
							</tr>
							<tr>
								<td>Imie: ${user.name}
							</tr>
							<tr>
								<td>Nazwisko: ${user.surname}
							</tr>
							<tr>
								<td>Telefon: ${user.phone}
							</tr>
							<tr>
								<td>E-mail: ${user.email}
							</tr>

							<tr>
						</table>
						<input type="submit" name="action"
							value="Edytuj dane" />

						<input type="submit" name="action"
							value="Zmień hasło" />

						<input type="submit" name="action"
							value="Usun konto" />

					</form>

				</section>
				<section class="tile">
					<h2>Rezerwacje:</h2>
					<ul>
					<c:forEach items="${codeList}" var="code">
						<li><a href="../check_booking?code=${code}">${code}</a></li>
					</c:forEach>
					</ul>
				</section>

			</section>

			<aside class="secondary">
				<section>


					<sec:authorize access="hasAnyRole('ADMIN','USER')">

						<p>
							Witaj <strong>${user.name}</strong>
						</p>
						<p><a href="user_show_profile">Zobacz swój profil</a></p>
					</sec:authorize>
				</section>

			</aside>


			<aside class="secondary">
				<h2>W najbliższym czasie:</h2>
				<c:forEach items="${seances}" var="seance">
					<section>
						<hr>
						<h2>
							<a href='../movie_detail?title=${seance.title}'>${seance.title}</a>
						</h2>
						<ul>
							<li><a href='../seance_detail?id=${seance.ID}'>${seance.startTime}</a></li>
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
