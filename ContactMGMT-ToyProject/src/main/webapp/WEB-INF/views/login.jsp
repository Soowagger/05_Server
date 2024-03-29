<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	
	<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<main>
		<div></div>
		<div>
			<h1>Start managing contacts</h1>
			<h3>ex) user01@kh.com / pass01</h3>
			
			<form action="/login" method="post" class="login-form">
				<p>Email Address</p>
				<input type="email" name="inputEmail" class=login-input required>
				
				<p>Password</p>
				<input type="password" name="inputPw" class=login-input required>
				<br>
				
				<button id="login-form-btn">Login</button>
				
			</form>	
		</div>
	</main>
	
	<c:if test="${not empty sessionScope.msg}">
		
		<script>
			alert('${msg}');
		</script>
		
		 <c:remove var="msg" scope="session"/>
	</c:if>
	
	<script src="/resources/js/login.js"></script>
</body>
</html>