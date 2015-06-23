<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

	<jsp:include page="/jsp/head.jsp" />
	
<body>

	<jsp:include page="/jsp/bootstrap-pre.jsp" />
	
	<spring:message code="accounts.regular" var="error409" />
	<input type="hidden" value="${error409}" id="error409" >
	
	<div class="container">
	
		<fmt:bundle basename="kuleuven-messages">
		
		<!-- modals -->
		
		<div id="error-div" class="modal hide" data-backdrop="static">
			<div class="modal-header alert-error">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4>
					<fmt:message key="create.result.error"/>
				</h4>
			</div>
			 <div class="modal-body">
				<p><fmt:message key="create.result.error.proceed.1"/></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="close.action"/></button>
			</div>
		</div>
		
		<div id="conflict-div" class="modal hide" data-backdrop="static">
			<div class="modal-header alert-error">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4>
					<fmt:message key="create.result.duplicate"/>
				</h4>
			</div>
			 <div class="modal-body">
				<span id="conflict"></span>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="close.action"/></button>
			</div>
		</div>
		
		<div id="success-div" class="modal hide" data-backdrop="static">
			<div class="modal-header alert-success">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4>
					<fmt:message key="create.result.success"/>
				</h4>
			</div>
			 <div class="modal-body">
			 	<p><fmt:message key="create.result.success.id"/>&nbsp;<strong class="userId"></strong>&nbsp;<span><fmt:message key="create.result.success.manage"/></span>&nbsp;<a href="mine.html"><fmt:message key="accounts.mine"/></a>.</p>
			 	<p><fmt:message key="create.result.proceed.1"/></p>
			 	<ul>
			 		<li><fmt:message key="create.result.proceed.2"/></li>
					<li><fmt:message key="create.result.proceed.3"/></li>
			 	</ul>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="close.action"/></button>
			</div>
		</div>
		
		<!-- form -->
		
		<form id="accountForm" action="#" method="post" class="form-horizontal" enctype="multipart/form-data">
	
			<div id="account-div" class="row">
					<fieldset>
						<legend>
							<fmt:message key="account.legend" />
						</legend>
						<!-- empty span is a trick to prevent a too large margin between the legend and the pretext -->
						<span></span>
						<div id="given" class="control-group">
							<label class="control-label"><fmt:message key="given.label"/>:</label>
							<div class="controls">
								<input id="givenName" type="text" class="input-xlarge" required>
							</div>
						</div>
						<div id="family" class="control-group">
							<label class="control-label"><fmt:message key="family.label"/>:</label>
							<div class="controls">
								<input id="familyName" type="text" class="input-xlarge" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="email"><fmt:message key="email.label"/>:</label>
							<div class="controls">
								<input id="email" type="email" class="input-xlarge" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$">
							</div>
						</div>
		    		
		    		</fieldset>
		    			
			</div>
			
			<div id="actions-div" class="row">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="enrollments"><fmt:message key="actions.label"/></label>
							<div class="controls">
								<button id="requestButton" type="submit" class="btn btn-primary" data-loading-text="in progress"><fmt:message key="request.label"/></button>
							</div>
						</div>
					</fieldset>
			</div>
			
			</form>
				
		</fmt:bundle>
		
	</div>
	
	<script src="javascript/players.js"></script>
	
</body>