<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>${error}</h2>
	<table border="1">
		<tr>
			<th>FullName</th>
			<th>Email</th>
			<th>Action</th>
		</tr>
		<c:forEach var="list" items="${listofUsers}">
			<tr>
				<td>${list.fullName}</td>
				<td>${list.email}</td>
				<td>
					<a href="/editUser?userId=${list.userId}">Edit</a>
					<a href="/deleteUser?userId=${list.userId}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>