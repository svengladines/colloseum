<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/jsp/head.jsp" />
	
<body>

	<div class="container">
	
    	<h1>Authorized ?</h1>
    	
    	<p>
    		<code>${authorized}</code>
    	</p>
    	
    	<p>
    		<strong>${error}</strong>
    	</p>
    	
    	<h2>Code</h2>
    	<p>
    		<strong>${code}</strong> <br/>
    		<a href="https://kuleuven-football.appspot.com/authorized.html?token-code=${code}&returnUrl=${returnUrl}">https://kuleuven-football.appspot.com/autorized.html?token-code=${code}&returnUrl=${returnUrl}</a>
    	</p>
    	
    	<h2>Token</h2>
    	<p>
    		<strong>${token}</strong> <br/>
    	</p>
    		
   	 </div>

</html>