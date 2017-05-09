<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
					<h2>Edycja "${title}"</h2>
					<p>${message}</p>
					<form:form action="admin_movies.html" method="POST" commandName="movieForm">
						<table>
							<tr>
								<td>Tytuł:</td>
								<td><form:input path="title"/></td>
							</tr>
							<tr>
								<td>Gatunek:</td>
								<td><form:input path="genre"/></td>
							</tr>
							<tr>
								<td>Premiera:</td>
								<td><form:input path="releaseYear"/></td>
							</tr>
							<tr>
								<td>Reżyser:</td>
								<td><form:input path="direction"/></td>
							</tr>
							<tr>
								<td>Scenariusz:</td>
								<td><form:input path="scenario"/></td>
							</tr>
							<tr>
								<td>Od lat:</td>
								<td><form:input path="pegi"/></td>
							</tr>
							<tr>
								<td>Czas trwania:</td>
								<td><form:input path="duration"/></td>
							</tr>
							<tr>
								<td>Opis:</td>
								<td><form:textarea path="description"/></td>
							</tr>
							<tr>
								<td>Plakat:</td>
								<td><form:textarea path="poster"/></td>
							</tr>
							<tr>
								<td>Zwiastun:</td>
								<td><form:textarea path="trailer"/></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" name="action" value="Zapisz zmiany" /></td>
							</tr>
						</table>
					</form:form>
				
			</section>
		</div>
	</div>
</body>
</html>
