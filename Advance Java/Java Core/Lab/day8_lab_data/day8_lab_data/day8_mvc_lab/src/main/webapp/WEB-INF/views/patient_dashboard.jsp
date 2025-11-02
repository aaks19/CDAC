<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL core tag library --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patient Dash Board</title>
</head>
<body>
	<c:if test="${not empty mesg}">
		<h5 style="color: blue;">${mesg}</h5>
		<c:remove var="mesg" scope="session" />
	</c:if>
	<h5>Hello , ${sessionScope.patient_details.name}</h5>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Upcoming Appointments</caption>
		<tr>
			<th>Appointment ID</th>
			<th>Date/Time</th>
			<th>Doctor Name</th>
			<th>Action</th>
		</tr>
		<c:forEach var="app" items="${requestScope.appointment_list}">
			<tr>
				<td>${app.appointmentId}</td>
				<td>${app.appointmentTS}</td>
				<td>${app.docName}</td>
				<td><a
					href="appointments?action=cancel&id=${app.appointmentId}"><button>Cancel</button></a></td>
			</tr>
		</c:forEach>
	</table>
	<h5>
		<a href="appointments?action=show_form">Book New Appointment</a>
	</h5>
	<h5>
		<a href="logout">Log out</a>
	</h5>
</body>
</html>