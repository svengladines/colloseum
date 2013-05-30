<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<jsp:include page="/jsp/head.jsp" />
	
<body>

	<fmt:bundle basename="tol-data-tools-messages">

	<jsp:include page="/jsp/bootstrap-pre.jsp" />
	
		<div class="span8">
	
				<div class="container-fluid">
			
					<div class="row-fluid">
				
						<div class="span12">
						
							<h3>
							</h3>
					
							<p>
							</p>
						</div>
					</div>
					
					<div class="row-fluid">
					
						<div id="meta" class="span12">
					
							<h4>
								Personen
							</h4>
							
							<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>Naam</th>
									<th>E-mail</th>
									<th>Acties</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<tr>
									<td colspan="3" class="value status" id="membershipsstatus"></td>
								</tr>
							</tbody>
							
							</table>
						</div>
					</div>
					
					<div class="row-fluid">
					
						<div id="meta" class="span12">
					
							<h4>
								Registreer
							</h4>
							
							<form class="form-horizontal">
									<div class="control-group">
										<label for="firstName" class="control-label">Voornaam</label>
										<div class="controls">
											<input id="firstName" type="text" class="input-large" placeholder="Homer"/>
										</div>
									</div>
									<div class="control-group">
										<label for="nick" class="control-label">Alias</label>
										<div class="controls">
											<input id="nick" type="text" class="input-large" placeholder="Jay"/>
										</div>
									</div>
									<div class="control-group">
										<label for="nick" class="control-label">Naam</label>
										<div class="controls">
											<input id="name" type="text" class="input-large" placeholder="Simpson"/>
										</div>
									</div>
									<div class="control-group">
										<label for="email" class="control-label">E-mail</label>
										<div class="controls">
											<input id="email" type="email" class="input-large" placeholder="homer.simpson@springfield.net"/>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button id="post" type="button" class="btn primary">Verstuur</button>
										</div>
									</div>
							</form>
							
						</div>
					</div>
			</div>
		</div>
		
		<div class="span2">
		
		</div>
		
	<script>

	var person 
			= null;

		function showPerson( person, tbody ) {

			var row
				= personAsRow( person );
			
			row.append( $jq( "<td/>" )
				.html( "" ) );
			
			tbody.append( row );
			
		}
		
		function showPersons( persons, tbody ) {
		
			tbody.html("");
			
			if ( ! $jq.isArray( persons ) ) {
				setStatus( "1 persoon geregistreerd" );
				showPerson( persons, tbody );
			}
			else if ( persons.length == 0 ) {
				return showNone();
			}
			else {
				if ( persons.length == 1 ) {
					setStatus( "1 persoon geregistreerd" );
				}
				else {
					setStatus( persons.length + " personen geregistreerd" );
				}
				$jq.each( persons, function( index, person ) {
					showPerson( person, tbody );
				});
			}
		}
		
		function setStatus( status, error ) {
			
			var brow
				= $jq("<tr/>");
	
			brow.append( $jq("<td/>" ).attr("id","status").attr("colspan","2").addClass("muted").html( status ) );
			
			brow.append( $jq("<td/>" ).attr("colspan","1").append( $jq("<a/>" ).attr("htref","javascript:void(0)").html("registreer") ) );
		
			$jq("#tbody").append( brow );
		
		}
		
		function showNone( ) {
			setStatus( "Geen personen geregistreerd" );
		}
		
		function register( ) {
			
			var person
				= null;
			
			$jq.ajax( {
				type: "get",
				url: personTemplateResource,
				dataType: "json",
				success: function( data ) {
					register2( data );
				},
				error: function() {
					;
				}
			});
			
		}
		
		function register2( person ) {
			
			person["@firstName"] 
				= $jq("#firstName").val();
		
			person["@name"] 
				= $jq("#name").val();
		
			person["@nickName"] 
				= $jq("#nick").val();
			
			person["@email"] 
				= $jq("#email").val();
		
			var json
				= JSON.stringify( person );
		
			$jq.ajax( {
				type: "POST",
				url: personsResource,
				dataType: "json",
				contentType : "application/json",
				data: json,
				success: function( data ) {
					;
				},
				error: function() {
					;
				}
		});
		}
		
		$jq("#post").click( function() {
			register();
		});
		
		$jq.ajax( {
				type: "GET",
				url: personsResource,
				dataType: "json",
				success: function( data ) {
					showPersons( data.person, $jq("#tbody") );
				},
				error: function() {
					;
				}
			});
			
	</script>
	
	</fmt:bundle>
	
	<jsp:include page="/jsp/bootstrap-post.jsp" />

</body>

</html>