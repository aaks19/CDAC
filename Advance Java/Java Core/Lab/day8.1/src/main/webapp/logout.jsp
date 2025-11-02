<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>
<%@page import="com.app.pojos.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Hello, ${user_dtls.name}</h5>
	<%
	User user = (User) session.getAttribute("user_dtls");
	int ageInYears = Period.between(user.getDob(), LocalDate.now()).getYears();
	%>
	<h5>
		Age -
		<%=ageInYears%></h5>
	<%-- invalidate session using EL syntax --%>
	${pageContext.session.invalidate()}
	<h5>You have logged out here....</h5>
	<h5>
		<a href="login.jsp">Visit Again</a>
	</h5>
</body>
</html>