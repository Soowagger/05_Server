<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign up</title>
	
	<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<main>
		<div></div>
		<div>
			<h1>Contact Management</h1>
			
			<form action="/signup" method="post" class="signup-form" onsubmit="return signCheck()">
				<p>Email Address</p>
				<input type="email" name="inputEmail" id="inputEmail" value="${inputEmail}" autocomplete="off" autofocus required>
				<span id="idMsg"></span>
				
				<p>Password</p>
				<input type="password" name="inputPw" id="inputPw" required>
				<span id="pwMsg">6~14자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.</span>
				
				<p>Confirm Password</p>
				<input type="password" name="inputPw2" id="inputPw2" required>
				<span id="pw2Msg"></span>
				
				<p>Name</p>
				<input type="text" name="inputName" id="inputName" required>
				<span id="nameMsg">2~5자의 한글만 사용해 주세요.</span>
				
				<p>Phone</p>
				<input type="tel" name="inputPhone" id="inputPhone" required>
				<br>
				
				<button id="signup-btn">Sign up</button>
			</form>	
		</div>
	
	</main>
	
	
	<script src="/resources/js/signup.js"></script>
</body>
</html>