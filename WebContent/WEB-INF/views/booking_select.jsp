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
					<h2>${message}</h2>
					<c:forEach items="${bookedSeats}" var="bookedSeat">
						<p>${bookedSeat}</p>
					</c:forEach>
					<form action="booking_select" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<input type="hidden" name="seanceID" value="${seanceID}" readonly/>
						<table>
						<tr>
								<td></td>
								<c:forEach var="i" begin="1" end="15">
									<td><c:out value="${i}" /></td>
								</c:forEach>
						</tr>
						<tr>
						<td>1</td>
						<c:set var="y" value="1"/>
						<c:set var="x" value="1"/>
						<c:forEach items="${seats}" var="seat">
							<c:forEach var="y" begin="${y}" end="${seat.rowNumber-1}">
								</tr><tr><td>${y+1}</td>
								<c:set var="x" value="1"/>
							</c:forEach>
							<c:set var="y" value="${seat.rowNumber}"/>
							<td>
							<c:forEach var="x" begin="${x}" end="${seat.seatNumber-1}">
								</td><td>
							</c:forEach>
							<c:choose>
    							<c:when test="${!seat.taken}">
        							<div class="checkBoxEnabled">
										<input type="checkbox" value="${seat.ID}" id="${seat.ID}" name="seat"/>
										<label for="${seat.ID}"></label>
									</div> 
    							</c:when>    
    							<c:otherwise>
        							<div class="checkBoxDisabled">
										<input type="checkbox" id="${seat.ID}" name="seat" disabled/>
										<label for="${seat.ID}"></label>
									</div> 
    							</c:otherwise>
							</c:choose>
							
							</td>
							<c:set var="x" value="${seat.seatNumber+1}"/>
						</c:forEach>
						</tr>
						</table>
						<input type="submit" value="Kup/zarezerwuj">
					</form>
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
