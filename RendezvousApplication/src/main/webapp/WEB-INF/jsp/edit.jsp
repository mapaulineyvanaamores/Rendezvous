<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>${error}</h2>
	<form action="/update" method="POST">
		<input type="hidden" name="userId" value="${user.userId}">
		<table border="1"> 
			<tr>
				<th>FullName</th>
				<td>
					<input type="text" name="fullname" value="${user.fullName}"></input>
				</td>
				<th>Email</th>
				<td>
					<input type="email" name="email" value="${user.email}"></input>
				</td>
			</tr>
		</table>
		<button type="submit">Update</button>
	</form>
</body>
</html>