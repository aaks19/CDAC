<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4 align="center">Welcome to Patient Dash Board....</h4>
	<c:if test="${not empty mesg}">
		<h4 style="color: blue;" align="center">${mesg}</h4>
		<c:remove var="mesg" scope="session" />
	</c:if>
	<h5>Hello , ${patient_details.name}</h5>

	<table style="background-color: lightgrey; margin: auto">
		<caption>Your Upcoming Appointments</caption>
		<tr>
			<th>Appointment ID</th>
			<th>Date-Time</th>
			<th>Doctor</th>
			<th>Action</th>
		</tr>
		<c:forEach var="appointment" items="${appointments}">
			<tr>
				<td>${appointment.appointmentId}</td>
				<td>${appointment.appointmentTS}</td>
				<td>${appointment.docName}</td>
				<td><a
					href="appointments?action=cancel&id=${appointment.appointmentId}"><button>Cancel</button></a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	<h4 align="center">
		<a href="appointments?action=show_book_form">Book New Appointment</a> 
		<a href="logout">Log Out</a> 
	
	</h4>
</body>
</html>