<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/jsp/head.jsp" />
	
<body>

<body>

  <script src="javascript/moment.min.js"></script>
  <script src="javascript/jquery-2.0.2.min.js"></script>
  <script src="javascript/underscore.js"></script>
  <script src="javascript/backbone.js"></script>
  <script src="bootstrap/js/bootstrap.js"></script>
  <script src="javascript/template.js"></script>
  
  <script type="text/javascript">
  	
  	var $jq = jQuery.noConflict();
  	
  </script>
  
  <header>
  	<div class="navbar navbar-inverse navbar-fixed-top">
		    <div class="navbar-inner">
		        <div class="container-fluid">
		        	<span class="brand">FC De Brodders</span>
		        	<div class="container">
			            <div class="nav-collapse">
			            </div>
			           <button id="loginButton" class="btn btn-primary navbar-next pull-right" type="button">Inloggen</button>
		            </div>
		            
		        </div>
		    </div>
		</div>
  </header>
  
  <div class="container">
  
    <div id="myCarousel" class="carousel slide">
    	<ol class="carousel-indicators">
    		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    		<li data-target="#myCarousel" data-slide-to="1"></li>
    		<li data-target="#myCarousel" data-slide-to="2"></li>
    	</ol>
    	
    	<!-- Carousel items -->
    	<div class="carousel-inner">
    	<div class="active item">
    		<img alt="" src="images/fans.jpg"></img>
    		<div class="carousel-caption">
    			<h4>'t Is nie gemakkelijk om geen kampioen te worden</h4>
    			<p>Onze slogan tijdens het seizoen 2008-2009</p>
    		</div>
    	</div>
    	
    	<div class="item">
    		two
    	</div>
    	
    	<div class="item">
    		three
    	</div>
    </div>
    
	    <!-- Carousel nav -->
    	<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
    	<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>
    
    </div>
    
    <script>
    
    	var $jq = jQuery.noConflict();
    	
	 	$jq("#loginButton").click( function() { window.location = "login.html"; } );
	    
    </script>

  </body>
</html>