<!-- My custom tag -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="code" required="false"%>
<%@ attribute name="message" required="false"%>
<%@ attribute name="exception" type="java.lang.Exception" required="false"%>
<%@ attribute name="errorMessage" required="false" %>

<h2>An error has occurred</h2>

<!-- Type error code if it not empty -->
<c:if test="${not empty code}">
	<h3>Error code: ${code}</h3>
</c:if>

<!-- Type error message if it not empty -->
<c:if test="${not empty message}">
	<h3>Message: ${message}</h3>
</c:if>

<!-- Type error exception if it not empty -->
<c:if test="${not empty exception}">
	 ${ exception.getMessage() }
</c:if>

<%-- if we get this page using forward--%>
<c:if test="${not empty errorMessage}">
	<h3>${errorMessage}</h3>
</c:if>