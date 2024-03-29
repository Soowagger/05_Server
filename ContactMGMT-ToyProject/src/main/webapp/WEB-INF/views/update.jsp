<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>연락처 수정</title>
	
	<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<main>
		<div>
			<a href="/logout" class="logout-btn">로그아웃</a>
		</div>
		<div>
			<h1>지금 연락처를 수정해보세요.</h1>
			
			<form action="/update" method="post" class="update-form">
				<p>이름</p>
				<input type="text" name="name" value="${contact.contactName}" required>
				
				<p>상세 정보</p>
				<input type="text" name="info" value="${contact.contactInfo}" required>
				
				<p>연락처</p>
				<input type="tel" name="phone" value="${contact.contactPhone}" required>
				
				<input name="contactNo" value="${contact.contactNo}" type="hidden">
				
				<br>
				
				<button id="update-btn">편집 완료</button>
			</form>
		
		</div>
	
	</main>
	
</body>
</html>