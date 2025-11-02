<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>
<%@page import="com.test.pojos.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Hello, ${user_dtls.name}</h4>
	<%
	User user = (User) session.getAttribute("user_dtls");
	int ageInYears = Period.between(user.getDob(), LocalDate.now()).getYears();
	%>
	<h4>
		Age -
		<%=ageInYears%></h4>
	<%
	session.invalidate();
	%>
	<h4>You have logged out...</h4>
	<h4>
		<a href="login.jsp">Visit Again...</a>
	</h4>
</body>
</html>