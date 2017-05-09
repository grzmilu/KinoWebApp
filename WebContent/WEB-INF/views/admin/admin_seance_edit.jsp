<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>


<spring:url value="/resources/style.css" var="styleCSS" />
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
				<h2>Edycja "${ID}"</h2>
				<p>${message}</p>
				<form:form action="admin_seances.html" method="POST"
					commandName="seanceForm">
					<form:hidden path="ID" />
					<table>
						<tr>
							<td>Data i godzina rozpoczęcia (rrrr-mm-dd hh:mm:ss):</td>
							<td><form:input path="startTime" /></td>
						</tr>
						<tr>
							<td>Numer sali:</td>
							<td><form:input path="roomNumber" /></td>
						</tr>
						<tr>
							<td>Tytuł filmu:</td>
							<td><form:input path="title" list="movieList" /></td>
							<datalist id="movieList">
								<c:forEach items="${movies}" var="movie">
									<option value="${movie.title}" />
								</c:forEach>
							</datalist>
						</tr>
						<tr>
							<td>ID Cennika:</td>
							<td><form:input path="priceID" list="priceList"/></td>
							<datalist id="priceList">
								<c:forEach items="${prices}" var="price">
									<option value="${price.id}">${price.name}</option>
								</c:forEach>
							</datalist>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" name="action"
								value="Zapisz zmiany" /></td>
						</tr>
					</table>
				</form:form>


			</section>
		</div>
	</div>
</body>
</html>
