<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!String message = "Hi there !";//private instance var.%>
<body>
	<%
	String message2 = "Hello";//method local - _jspService
	pageContext.setAttribute("nm", 12345);//nm - page scoped attribute
	%>
	<%@ include file="test8.jsp"%>
</body>
</html>