<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>from next  page ....</h5>
	<%-- render product details EL syntax --%>
	<h5>Product Details - ${requestScope.product_details}</h5>
	<h5>param - ${param}</h5>
</body>
</html>