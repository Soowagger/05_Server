<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert Contact</title>
	
	<link rel="stylesheet" href="/resources/css/main-style.css">
</head>
<body>
	<main>
		<div>
			<a href="/logout" class="logout-btn">로그아웃</a>
		</div>
		<div>
			<h1>지금 연락처를 등록해보세요.</h1>
			
			<form action="/insert" method="post" class="insert-form">
				<p>이름</p>
				<input type="text" name="name" required>
				
				<p>상세 정보</p>
				<input type="text" name="info" required>
				
				<p>연락처</p>
				<input type="tel" name="phone"  required>
											
				<br>
				
				<button id="insert-btn">등록 완료</button>
			</form>
		
		</div>
	
	</main>
</body>
</html>