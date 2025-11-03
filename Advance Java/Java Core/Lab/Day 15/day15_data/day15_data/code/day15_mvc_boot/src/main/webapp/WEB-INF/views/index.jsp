<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Welcome To Spring MVC , Server Time
		${requestScope.server_time}</h4>
		<%--http://host:port/ctx_path/department/list --%>
	<h5>
		<a href="department/list">List All Departments</a>
	</h5>
</body>
</html>