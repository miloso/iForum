<%-- 
    Document   : registererror
    Created on : 15.11.2011, 22:27:33
    Author     : Milan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="validationErrors" scope="request" class="net.milanvit.iforum.models.ValidationErrors" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iForum | registration error</title>
    </head>
    <body>
        <h1>iForum | registration error</h1>
		
		<p>There have been following errors with your registration:</p>

		<ul>
			<c:forEach var="errorMessage" items="${validationErrors.errorMessages}">
				<li>${errorMessage}</li>
			</c:forEach>
			<% validationErrors.emptyMessages (); %>
		</ul>
		
		<p><a href="javascript:history.back ()">Try again!</a></p>
    </body>
</html>
