<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/head.jsp" />
	
<body>

	<jsp:include page="/WEB-INF/jsp/bootstrap-pre.jsp" />
  
<body>

	<div class="container">
	
    	<div class="row">
    
    		<div id="calendar" class="span12">

			    <div id="main" class="ranking">
			    
			    <br/>
			    
			      <table class="table table-striped table-bordered">
			      <thead>
			      	<tr>
			      		<th>Ploeg</th>
			      		<th>Gespeeld</th>
			      		<th>Gewonnen</th>
			      		<th>Verloren</th>
			      		<th>Gelijk</th>
			      		<th>Goals voor</th>
			      		<th>Goals tegen</th>
			      		<th>Punten</th>
			      	</tr>
			      </thead>
			      <tbody id="rankings">
			      
			      </tbody>
			      </table>
			      
			    <footer>
			    	<span class="label">Deze stand is gebaseerd op data van <a href="http://www.kblvb.be">www.kblvb.be</a> </span>
			    </footer>
			
			  </div>
   	 		</div>
   	 		
   	 	</div>
   	 </div>
  
   <script type="text/javascript">
  	
  	var $jq = jQuery.noConflict();
  	
  </script>

  <script src="javascript/ranking.js"></script>
  
  <script>
  
  	var rankings = rankings();
  	var view = view( rankings );
  	var router = router( rankings );
  	
  	router.query( rankings );
  
  </script>
  
</html>