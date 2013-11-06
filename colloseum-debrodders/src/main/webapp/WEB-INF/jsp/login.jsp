<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/jsp/head.jsp" />
	
<body>

  <script type="text/javascript">
  	
  	var $jq = jQuery.noConflict();
  	
  </script>
  
  <div class="container">
  	
  	<div class="row">
	  		
	    <form class="form-horizontal">
				 <div class="control-group">
					 <label class="control-label" for="brodderEmail">Email</label>
					<div class="controls">
						<input type="text" id="brodderEmail" placeholder="Email">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="brodderPassWord">Password</label>
					<div class="controls">
						<input type="password" id="brodderPassWord" placeholder="Password">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
					<!-- 
						<label class="checkbox">
						<input type="checkbox"> Keep me logged in
						</label>
					 -->
						<button id="inButton" type="button" class="btn btn-primary">Inloggen</button>
					</div>
				</div>
			</form>
	  		
	 </div>
	  		
  	</div>
  	
    <script>
    
    	var $jq = jQuery.noConflict();
    	
    	var get = function( callback ) {
    		
    		var crd = null;
    		
    		$jq.ajax( {
				type: "GET",
				url: "rs/credentials/template",
				dataType: "json",
				success: function( responseCredential ) {
					
					callback( responseCredential );
					
				},
				error: function( response ) {
					alert( response );
				}
			});
    		
    		return crd;
    		
    	}
    
	    var post = function( credential ) {
	    	
	    	var cred
	    		= gather( credential );
			
			$jq.ajax( {
				type: "PUT",
				url: "rs/credentials/" + encodeURIComponent( credential.reference ) + ".json",
				// type: "POST",
				// url: "rs/credentials",
				dataType: "json",
				contentType: "application/json",
				data: JSON.stringify( cred ),
				success: function( responseCredential ) {
					window.location = responseCredential.protectedUrl;
				},
				error: function( response ) {
					alert( response );
				}
			});
			
		}
	    
	    var gather = function( credential) {
	    	
	    	credential.reference = $jq("#brodderEmail").val() ;
	    	credential.passWord = $jq("#brodderPassWord").val() ;
	    	credential.status = "Check" ;
	    	
	    	return credential;
	    	
	    	
	    }
	    
	 $jq("#inButton").click( function() { get( post ); } );
	    
    </script>

  </body>
</html>