<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/jsp/head.jsp" />
	
<body>

	<jsp:include page="/jsp/bootstrap-pre.jsp" />
	
	<div class="container">
	
    	<div class="row">
    	
    		<fmt:bundle basename="kuleuven-messages">
    
    		<div id="content" class="span12">
    		
    			<table class="table table-condensed table-bordered">
    			<tr>
					<th>Match</th><td>${registration.match.homeTeam.name} - ${registration.match.awayTeam.name}</td>
				</tr>
				<tr>
					<th>Date</th><td>${date}</td>
				</tr>
				<tr>
					<th>Kick-off</th><td>${kick}</td>
				</tr>
				<tr>
					<th>Your status</th>
						<td>
							<c:choose>
							<c:when test="${registration.rsvp.answer == 'Yep'}">
								<span><fmt:message key="registration.yep"/></span> &nbsp;[<a href="${nope}"><fmt:message key="registration.cancel"/></a>]								
							</c:when>
							<c:otherwise>
								<span><fmt:message key="registration.nope"/></span> &nbsp;[<a href="${yep}"><fmt:message key="registration.register"/></a>]
							</c:otherwise>
							</c:choose>
						</td>
				</tr>
				<tfoot>
						<tr>
							<td colspan="2"><span><fmt:message key="registration.details"/></span>&nbsp;<a href="https://www.kuleuven.be/voetbal/kalender2.php"><fmt:message key="calendar"/></a></td>
						</tr>
				<tfoot>
				</table>
    			
    		</div>
    		
    		</fmt:bundle>
    		
    	</div>
    	
    </div>
	
	<jsp:include page="/jsp/bootstrap-post.jsp" />
	
</body>

</html>