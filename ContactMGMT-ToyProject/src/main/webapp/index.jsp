<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Contact Management</title>
	
	<%-- CSS 파일 연결 --%>
	<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<main>
		 <c:choose>
		 	<c:when test="${empty sessionScope.loginMember}">
		 		<h1>Contact Management</h1>
		 		<br>
		 		<h2>간편하고 신속한 연락처 관리</h2>
		 		<br>
		 		<h2>지금 시작하시려면 이메일 주소를 입력해주세요.</h2>
		 		<br>
		 		
		 	<form action="/signup" method="post" class="signup-main">
	 			<input type="email" name="inputEmail" placeholder="이메일 입력" class="email-input">
	 			<button class="signup-btn">Sign up</button>
	 			
	 			<!-- 아닙니다. 로그인하겠습니다. 추가부터 시작 -->
			</form>	 			
					 
		 	</c:when>
		 
		 </c:choose>
	
	
	
	</main>
	
	
</body>
</html>