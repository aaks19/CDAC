<%@page import="com.app.core.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing forward / include actions</title>
</head>
<body>
	<h5>from 1st page ....</h5>
	<%
	out.flush();
	int id = Integer.parseInt(request.getParameter("product_id"));
	String pName = request.getParameter("name");
	double price = Double.parseDouble(request.getParameter("price"));
	Product product = new Product(id, pName, price);
	//min scope of attributes for RD (server pull) - request
	request.setAttribute("product_details", product);
	%>
	<jsp:include page="test10.jsp">
		<jsp:param value="stationary" name="category" />
	</jsp:include>
</body>
</html>