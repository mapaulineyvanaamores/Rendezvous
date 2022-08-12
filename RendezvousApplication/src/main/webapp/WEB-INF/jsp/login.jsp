<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>lOGIN PAGE</title>
<script type="text/javascript">
	function saveUser(){
		let email = document.getElementById("email");
		let password = document.getElementById("password");
		if (email.value=="" ||password.value==""){
			alert("Fields cannot be blank!")
			return false;
		} else{
			return true;
		}
	}
</script>
</head>
<body>
	<h2>USER LOGIN</h2>
	<form action ="/validate" method="post">
	<%-- <form action ="/validateEmail" method="post">  --%>
		Email Address: <input type="email" id="email" name="email"><br>
		Password: <input type="password" id="pasword" name="password"><br>
		<button type="submit">Login</button>
	</form>
	<h2 style="color:red;">${error}</h2>
</body>
</html>