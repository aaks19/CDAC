<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor Dashboard</title>
</head>
<body>
    <c:if test="${not empty mesg}">
        <h5 style="color:blue;">${mesg}</h5>
        <c:remove var="mesg" scope="session" />
    </c:if>

    <h3>Hello, ${sessionScope.doctor_details.docName}</h3>
    <p>Welcome to your dashboard!</p>
</body>
</html>
