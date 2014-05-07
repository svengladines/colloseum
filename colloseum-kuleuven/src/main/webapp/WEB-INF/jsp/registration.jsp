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
    
    		<div id="content" class="span12">

				<c:choose>
				
					<c:when test="${ not empty registrations }">
					
						<c:forEach items="${registrations}" var="registration">
							<c:out value="${registration.player.givenName}"/>:
							<c:out value="${registration.rsvp.answer}"/>
						</c:forEach>
						
					</c:when>
					
					<c:otherwise>
						<script>
  							var r = new Registration( '${param.match}','${param.player}','${param.rsvp}' );
  							postRegistration( r );
  						</script>
					</c:otherwise>
				
				</c:choose>
    			
    		</div>
    		
    	</div>
    	
    </div>
	
	<jsp:include page="/jsp/bootstrap-post.jsp" />
	
</body>

</html>