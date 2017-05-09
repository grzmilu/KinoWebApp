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
						<li><a href='/login'>Zaloguj</a></li>
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
					<h2>${message}</h2>
					<sec:authorize access="hasRole('ANONYMOUS')">
					<p>Rezerwacja na anonimowego użytkownika:</p>
					</sec:authorize>
					<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
					<p>Rezerwacja na użytkownika ${user.name}:</p>
					</sec:authorize>
					<table>
						<tr>
							<td>Rząd</td><td>Miejsce</td><td>Bilet</td>
						</tr>
						<form action="booking_detail" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<input type="hidden" name="seanceID" value="${seanceID}" readonly/>
							<c:forEach items="${seats}" var="seat">
							<input type="hidden" name="seatID" value="${seat.ID}" readonly/>
								<tr>
									<td>${seat.rowNumber}</td>
									<td>${seat.seatNumber}</td>
									<td><select name="lowerPrice"><option value="false">Normalny</option><option value="true">Ulgowy</option></select></td>
								</tr>
							</c:forEach>
							<tr>
								<td><input type="submit" name="action" value="Zarezerwuj"></td><td><input type="submit" name="action" value="Kup"></td>
							</tr>			
						</form>
					</table>
				</section>
			</section>
		</div>

		<footer class="clear">
			<section class="footer">
				<p>Copyright © 2016 | Grupa C</p>
			</section>
		</footer>
	</div>

</body>

</html>
