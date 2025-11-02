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
	//forward the client to test3.jsp , in the SAME request.
	//1. Create RequestDispatcher (wrapper) object to create the chain of resources
	RequestDispatcher rd = request.getRequestDispatcher("test3.jsp");
	rd.forward(request, response);
	/*
	 WC - clears the resp buffer(out : JspWriter) -> 
	-> invokes test3_jsp's _jspService -> after it's exec (test5 can generate resp)
	 -> control comes back
	 -> resp is rendered.

	*/
	%>
</body>
</html>