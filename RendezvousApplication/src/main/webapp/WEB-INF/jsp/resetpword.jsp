<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/savepass">
		<input type="hidden" name="email" value="email">
		Enter your new password:
		<input type="password" name="password">
		<button type="submit">Reset</button>
	</form>
</body>
</html>