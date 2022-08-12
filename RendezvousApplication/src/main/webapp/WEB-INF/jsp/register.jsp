<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<script type="text/javascript">
	function saveUser(){
		let email = document.getElementById("email");
		let fullname = document.getElementById("fullname");
		let password = document.getElementById("password");
		let confirmpassword = document.getElementById("confirmpassword");
		if (email.value=="" || fullname.value=="" || password.value=="" || confirmpassword.value==""){
			alert("Fields cannot be blank!")
			return false;
		} else{
			return true;
		}
	}
</script>
</head>
<body>
	<h1>USER REGISTRATION</h1>
	<form action ="/saveUser" method="post">
		Email: <input type="email" id="email" name="email"> <br>
		Full Name: <input type="text" id="fullname" name="fullname"> <br>
		Password: <input type="password" id="password" name="password"> <br>
		Confirm Password: <input type="password" id="confirmpassword" name="confirmpassword"> <br>
		<button type="submit">Save Registration</button>
	</form>
	<h2 style="color:red;">${error}</h2>
</body>
</html>