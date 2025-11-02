<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.HashMap,com.app.pojos.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	HashMap<String, User> users;

	public void jspInit() {
		users = new HashMap<>();
		//populate the map
		users.put("rama@gmail.com",
				new User("Rama Patil", "rama@gmail.com", "rama@123", LocalDate.parse("1990-10-02")));
		users.put("kiran@gmail.com",
				new User("Kiran Joshi", "kiran@gmail.com", "kiran@123", LocalDate.parse("1992-10-02")));
		System.out.println("map populated : jspInit");
	}%>
<body>
	<%
	System.out.println("validation login - scriptlet - _jspService");
	//validate email
	User user = users.get(request.getParameter("em"));
	if (user != null) {
		//=> email valid -> chk password
		if (user.getPassword().equals(request.getParameter("pass"))) {
			//=> valid password -> store user details (pojo) under session scope
			session.setAttribute("user_dtls", user);
			//redirect the client
			response.sendRedirect(response.encodeRedirectURL("details.jsp"));
		} else {
			//invalid password
	%>
	<h5>
		Invalid Password , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	} else {
	//invalid email
	%>
	<h5>
		Invalid Email , Please <a href="login.jsp">Retry</a>
	</h5>

	<%
	}
	%>
</body>
</html>