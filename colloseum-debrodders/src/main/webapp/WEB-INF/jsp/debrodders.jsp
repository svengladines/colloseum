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
    
    		<div id="left" class="span2">
    			
    		</div>
    		
    		<div id="right" class="span8">
    			
    			<div class="row">
    				<form id="dropForm" class="form-inline">
    					<div class="input-prepend">
    						<!-- <button type="button" class="btn btn-primary">Upload</button>  -->
    						<input id="dropZone" type="text" class="input-xxlarge" required/>
    					</div>
    					<!-- <button type="button" class="btn btn-primary">Maak</button> -->
   					</form>
    			</div>
    			
    			<div id="publits class="row">
    				
    			</div>
    			
    		</div>
    		
    	</div>
    	
    </div>
	
	<jsp:include page="/jsp/bootstrap-post.jsp" />
	
	<script src="javascript/moment.min.js"></script>
  	<script src="javascript/jquery-1.7.2.min.js"></script>
  	<script src="javascript/underscore.js"></script>
  	<script src="javascript/backbone.js"></script>
  
  
	  <script type="text/javascript">
	  	
	  	var $jq = jQuery.noConflict();
	  	
	  </script>
  
  	<script>
  	
  		$jq("#drop").on("all", function( event ) {
  			console.log("event detected, event is ", event );
  		});
  	
		$jq("#dropZone").on("input", function( event ) {
  			
			console.log("input event detected, value is ", $jq(this).val() );
			
			var input
				= $jq(this).val();
			
			if ( ( input.indexOf("http://") == 0 ) || (  input.indexOf("https://") ) ) {
				
				// post URL to server
				
			}
  			
  		});
		
		$jq("#dropForm").submit( function( event ) {
			
			event.preventDefault();
  			
			console.log("form submit detected, value is ", $jq("#dropZone").val() );
						
  		});
		
  	</script>
  	
  	<script src="javascript/publits.js"></script>

</body>

</html>