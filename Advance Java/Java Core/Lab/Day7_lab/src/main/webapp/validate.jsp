<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap, java.util.Map, com.test.pojos.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<%!//JSP declaration block
	//declare a hashmap
	private HashMap<String, User> userMap;

	public void jspInit() {
		userMap = new HashMap<>();
		//populate the map
		userMap.put("aks@gmail.com", new User("Akshat", "aks@gmail.com", "a123", LocalDate.parse("2003-06-19")));
		userMap.put("neha@gmail.com", new User("Neha", "neha@gmail.com", "n123", LocalDate.parse("2004-12-29")));
		userMap.put("rohit@gmail.com", new User("Rohit", "rohit@gmail.com", "r123", LocalDate.parse("2002-01-02")));
	}%>
<body>
	<%
	System.out.println("Validation login - scriptlet - _jspService");
	//validate user
	User user = userMap.get(request.getParameter("em"));
	if(user!=null){
		//-> email is valid
		if(user.getPassword().equals(request.getParameter("pass"))){
			//->valid password -> store user details (pojo) under session scope -> redirect the client
			session.setAttribute("user_dtls",user);
			
			//redirect
			response.sendRedirect("details.jsp");
		}else{
			//invalid password
			%>
			<h5>Invalid Password, please <a href="login.jsp">retry</a></h5>
			<%
		}
	}else{
		//invalid email
		%>
		<h5>Invalid email, please <a href="login.jsp">retry</a></h5>
		<%
	}
	%>
</body>
</html>