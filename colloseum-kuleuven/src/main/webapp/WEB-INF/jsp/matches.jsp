<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/head.jsp" />
	
<body>

	<section id="matches">

	<div class="container">
	
		<table class="table table-striped table-bordered">
		
			<thead>
				<tr>
		      		<th>Datum</th>
		      		<th>Thuisploeg</th>
		      		<th>Bezoekers</th>
		      		<th>Aftrap</th>
		      		<th>Uitslag</th>
			     </tr>
			</thead>
			
			<tbody>
				
				<c:forEach items="${matches}" var="match">
				
				<fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${match.starts}" var="date"></fmt:formatDate>
				<tr>
					<td>${date}</td>
					<td>${match.homeTeam.name}</td>
					<td>${match.awayTeam.name}</td>
					<td></td>
					<td></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</section>
	
	</body>