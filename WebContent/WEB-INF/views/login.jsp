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
					<sec:authorize access="hasRole('ANONYMOUS')">
						<li><a href='anon_booking'>Rezerwacje</a></li>
					</sec:authorize>
					<li><a class="active" href='login'>Zaloguj</a></li>
				</ul>
			</nav>
		</header>


		<div id="content" role="main">
			<section class="primary">
				<section class="tile_wide">
					<h2>Logowanie</h2>

					<c:set var="loginUrl">
						<c:url value="/login" />
					</c:set>
					<form method="post" action="${loginUrl}">
						<c:if test="${param.error != null}">

							<p>Invalid usernamee and password.</p>

						</c:if>

						<c:if test="${param.logout != null}">
							<p>You have been logged out.</p>
						</c:if>

						<table>
							<tr>
								<td>Login:</td>
								<td><input type="text" name="login" /></td>
							</tr>

							<tr>
								<td>Hasło:</td>
								<td><input type="password" name="password" /></td>
							</tr>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<tr>
								<td colspan="2"><input type="submit" value="Zaloguj się" /></td>
							</tr>





						</table>
					</form>


					Nie masz konta? Zarejestruj się <a href='create'>tutaj</a>
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
