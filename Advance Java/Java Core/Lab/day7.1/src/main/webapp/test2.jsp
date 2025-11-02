<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- Create 4 different attributes & store them under different scopes --%>
	<%
	//add an attrbute under page scope
	pageContext.setAttribute("nm1", 100);//auto boxing-> up casting
	//add an attrbute under request scope
	request.setAttribute("nm2", 200);
	//add an attrbute under session scope
	session.setAttribute("nm3", 300);
	//add an attrbute under application(ServletContext) scope
	application.setAttribute("nm4", 400);
	//redirect the client to test3.jsp , in the NEXT request from the client
	response.sendRedirect("test3.jsp");
	/*
	 WC - clears the resp buffer(out : JspWriter) -> sends redirect resp
	 SC 302 , Location- test3.jsp , body - empty
	 Web browser -> sends a NEW request 
	 URL - http://host:port/ctx_path/test3.jsp
	*/
	%>
</body>
</html>