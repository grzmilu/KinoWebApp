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
					<li><a href='../price_list?day=0'>Cennik</a></li>
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
					<h2>Zmień hasło:</h2>
					<form:form action="user_show_profile.html" method="POST"
						commandName="userForm">
						<table>

							<p>
								<error>${errorMessage}</error>
							</p>

							<tr>
								<td>Stare hasło:</td>
								<td><input type="password" name="oldPass" /></td>
							</tr>
							<tr>
								<td>Nowe hasło:</td>
								<td><input type="password" name="newPass" /></td>
							</tr>
							<tr>
								<td>Potwierdź nowe hasło:</td>
								<td><input type="password" name="newPassRepeat" /></td>
							</tr>

							<tr>
								<td colspan="2"><input type="submit" name="action"
									value="Zatwierdź" /></td>
								<td colspan="2"><input type="submit" name="action"
									value="Wróć" /></td>
							</tr>
						</table>
					</form:form>

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
