<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Session ID - ${pageContext.session.id}</h5>
	<%--render session expiration timeout --%>
	<h5>Session expiration time - ${pageContext.session.maxInactiveInterval}</h5>
	<%-- context path --%>
	<h5> Context path - ${pageContext.request.contextPath}</h5>
	<h4 align="center">User Details Via Scriptlet</h4>
	<%
	String email = request.getParameter("em");
	String password = request.getParameter("pass");
	out.print("<h5> Email " + email + "</h5>");
	out.print("<h5> Password " + password + "</h5>");
	%>
	<hr />
	<h4 align="center">User Details Via Expression</h4>
	<h5>
		Email -
		<%=request.getParameter("em")%></h5>
	<h5>
		Password -
		<%=request.getParameter("pass")%></h5>
	<hr />
	<h4 align="center">User Details Via EL Syntax</h4>
	<h5>Email - ${param.em}</h5>
	<h5>Password - ${param.pass}</h5>
	<h5> Request param map - ${param}</h5>
	<h5> Session <%= session %></h5>
	<h5>Session via EL syntax - ${pageContext.session}</h5>
	<h5> page - <%= page %></h5>
	<h5> pageContext - <%= pageContext %></h5>
	<h5>pageScope - ${pageScope}</h5>
	<h5>out - <%= out %></h5>
</body>
</html>