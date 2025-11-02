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
	<c:if test="${not empty param.err_mesg}">
		<h5 style="color: red;" align="center">${param.err_mesg}</h5>
	</c:if>
	<form action="appointments" method="post">
		<input type="hidden" name="action" value="book" />
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Choose Doctor</td>
				<td><select name="doc_id">
						<c:forEach var="doc" items="${doctors}">
							<option value="${doc.id}">${doc.docName}
								${doc.speciality}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td>Appointment Date Time</td>
				<td><input type="datetime-local" name="appointment_ts" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Book Appointment" /></td>
			</tr>
		</table>
	</form>

</body>
</html>