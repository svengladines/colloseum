<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/jsp/head.jsp" />
	
<body>

	<jsp:include page="/jsp/bootstrap-pre.jsp" />
  
<body>

	<div class="container">
	
    	<div class="row-fluid">
    
    		<div id="calendar" class="span12">

			    <div id="main" class="calendar">
			    
			    <br/>
			    
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
			      <tbody id="matches">
			      
			      </tbody>
			      </table>
			      
			    <footer>
			    </footer>
			
			  </div>
   	 		</div>
   	 	</div>
   	 </div>

  <script src="javascript/schedule.js"></script>
  
</html>