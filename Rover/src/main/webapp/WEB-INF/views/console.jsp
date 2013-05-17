<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Plateau Rover</title>
</head>
<body>
	<h1>Welcome explorers!</h1>

	<form:form commandName="command" method="POST">
		<form:label path="value">Command</form:label>
		<form:input path="value" />
		
		<input type="submit" value="Send">
	</form:form>

	<c:forEach var="line" items="${history}">
		${line}
		<br>
		<br>
	</c:forEach>	
</body>
</html>