<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centralized Error Handling Page</title>
</head>
<body>
<h5 style="color: red;">Error Message - <%= exception.getMessage() %></h5>
<h5 style="color: red;">Error Details - <%= exception %></h5>
<hr/>
<h4>Error Details Via EL syntax</h4>
<h5 style="color: red;">Error Message - ${pageContext.exception.message} </h5>
<h5 style="color: red;">Error Details - ${pageContext.exception} </h5>
<h5 style="color: red;">Error Code - ${pageContext.errorData.statusCode}</h5>
<h5 style="color: red;">Error Causing Page  - ${pageContext.errorData.requestURI}</h5>

</body>

</html>