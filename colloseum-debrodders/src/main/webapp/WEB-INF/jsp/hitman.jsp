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
	
    	<div class="row">
    
    		<div id="hitmen" class="span12">

			    <div id="main">
			    
			    <br/>
			    
			      <table class="table table-striped table-bordered">
			      <thead>
			      	<tr>
			      		<th>Speler</th>
			      		<th>Goals</th>
			      	</tr>
			      </thead>
			      <tbody id="hitlist">
			      
			      </tbody>
			      </table>
			      
			    <footer>
			    </footer>
			
			  </div>
   	 		</div>
   	 		
   	 	</div>
   	 </div>
  
   <script type="text/javascript">
  	
  	var $jq = jQuery.noConflict();
  	
  </script>

  <script src="javascript/hitmen.js"></script>
  
  <script>
  
  	var hitmen = hitmen();
  	var view = view( hitmen );
  	var router = router( hitmen );
  	
  	router.query( hitmen );
  
  </script>
  
</html>