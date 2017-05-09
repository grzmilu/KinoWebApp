<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
			<h1 id="logo">Panel administratora</h1>

			<nav class="clear">
				<ul>
					<li><a href='admin_movies.html'>Filmy</a></li>
					<li><a href='admin_seances.html'>Seanse</a></li>
					<li><a class="active" href='admin_users.html'>Użytkownicy</a></li>
					<li><a href='/KinoWebApp/'>Strona główna</a></li>
				</ul>
			</nav>
		</header>


		<div id="content" role="main">
			<section class="primary">
				<form action="admin_users.html" method="POST">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="login"
						value="" readonly /> <input type="submit" name="action"
						value="Dodaj nowy" />
				</form>
				<c:forEach items="${users}" var="user">
				
						<form action="admin_users.html" method="POST">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table>
								<tr>
									<td><input type="text" name="login" value="${user.login}"
										readonly />
									<td><input type="submit" name="action" value="Edytuj" /><input
										type="submit" name="action" value="Skasuj" /></td>
								</tr>



							</table>
						</form>
					
				</c:forEach>

			</section>
		</div>
	</div>
</body>
</html>
