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
		</header>


		<div id="content" role="main">
			<section class="primary">
				<h2>Edycja "${login}"</h2>
				<p>${message}</p>
				<form:form action="admin_users.html" method="POST"
					commandName="userForm">
					<table>
						<tr>
							<td>Login:</td>
							<td><form:input path="login" /></td>
						</tr>
						
						
					
						<tr>
							<td>Numer telefonu:</td>
							<td><form:input path="phone" /></td>
						</tr>
						<tr>
							<td>E-mail:</td>
							<td><form:input path="email" /></td>
						</tr>
						<tr>
							<td>Imię:</td>
							<td><form:input path="name" /></td>
						</tr>
						<tr>
							<td>Nazwisko:</td>
							<td><form:input path="surname" /></td>
						</tr>

						<tr>
							<td>Rola:</td>
							<td><form:input path="authorities" /></td>
								<td>Hasło:</td>
							<td><form:input path="password"/></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="action"
								value="Zapisz zmiany" /></td>
									<td colspan="2"><input type="submit" name="action"
								value="Zmień hasło" /></td>
						</tr>
							
						
						
					</table>
				</form:form>

			</section>
		</div>
	</div>
</body>
</html>
