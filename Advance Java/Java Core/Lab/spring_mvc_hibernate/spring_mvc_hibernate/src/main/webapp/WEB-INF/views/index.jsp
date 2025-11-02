<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
</head>
<body>
	<h1>Welcome to Spring MVC</h1>
	<h2>Server time = ${requestScope.server_time}</h2>
	
	<%-- http://host:port/ctx_path/department/list --%>
	<h3><a href="department/list">List All Department</a></h3>
</body>
</html>