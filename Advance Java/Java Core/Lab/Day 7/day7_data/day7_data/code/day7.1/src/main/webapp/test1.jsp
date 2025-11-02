<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%-- JSP declaration block --%>
<%!// JSP declaration block
	int visits;

	public void jspInit() {
		System.out.println("in jsp init - " + Thread.currentThread());
	}

	int updateVisits() {
		return ++visits;
	}%>
<body>
	<%
	out.print("in jsp service " + Thread.currentThread());
	%>
	<h5>
		Page Visits -
		<%=updateVisits()%></h5>
</body>
<%!public void jspDestroy() {
		System.out.println("in jsp destroy " + Thread.currentThread());
	}%>

</html>