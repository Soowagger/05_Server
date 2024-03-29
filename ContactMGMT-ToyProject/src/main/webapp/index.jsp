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
	<%-- 미로그인 --%>
	<main>
		<c:choose>
			<c:when test="${empty sessionScope.loginMember}">
				<div class=loginBtn-area>
					<a href="/login" class="login-btn" >로그인</a>
				</div>
			
				<div class=singupBtn-area>
					 <c:choose>
					 	<c:when test="${empty sessionScope.loginMember}">
					 		<h1>Contact Management</h1>
					 		<br>
					 		<h2>간편하고 신속한 연락처 관리</h2>
					 		<br>
					 		<h2>지금 시작하시려면 이메일 주소를 입력해주세요.</h2>
					 		<br>
					 		
					 	<form action="/signup" method="get" class="signup-main">
				 			<input type="email" name="inputEmail" placeholder="이메일 입력" 
				 			class="email-input" autocomplete="off">
				 			<button class="signup-btn">Sign up</button>
				 					
						</form>	 			
								 
					 	</c:when>
					 
					 </c:choose>
				</div>
			</c:when>
			
			<%-- 로그인 했다면 --%>
			<c:otherwise>
						
					<c:choose>
						<%-- 없다면 --%>
							<c:when test="${empty ctList}">
							<div>
								<span>${loginMember.memberName}님 대단히 반갑습니다!</span>&nbsp;&nbsp;&nbsp;
								<a href="/logout" class="logout-btn">로그아웃</a>
							</div>
							<div class="non-contact-div">
								<h2>${loginMember.memberName}님의 등록된 연락처가 없습니다.</h2>
								<br>
								<label>	
									<h2>연락처를 등록해보시겠습니까? 
									<a href="/insert" class="insert-btn">등록하기</a></h2>
									<!-- <a href="/insert" class="insert-btn">등록하기</a> -->
								</label>								
							</div>
							</c:when>
							
						<%-- 있다면 --%>	
						<c:otherwise>
							<div>
								<span>${loginMember.memberName}님 대단히 반갑습니다!</span>&nbsp;&nbsp;&nbsp;
								<a href="/logout" class="logout-btn">로그아웃</a>
							</div>
							<div>
								<h1>${loginMember.memberName}'s Contact List</h1>

								<table>
									<tr>
										<th>즐겨찾기</th>
										<th>이름</th>
										<th>등록정보</th>
										<th>등록일</th>
										<td colspan="2"><a href="/insert" class="insert-btn">등록하기</a></td>
										
									</tr>
									<c:forEach var="contact" items="${ctList}">
										<tr>
											<td align="center"><a class="star" onclick="return changeStar(this)">☆</a></td>
											<td>${contact.contactName}</td>
											<td>${contact.contactInfo}</td>
											<td>${contact.contactDate}</td>
											<td><a href="/update?contactNo=${contact.contactNo}" class="update-btn">편집</a></td>
											<td><a href="/delete?contactNo=${contact.contactNo}" class="delete-btn" 
											onclick="return confirm('삭제하시면 되돌릴 수 없습니다. 그래도 삭제하시겠습니까?')">삭제</a></td>
										</tr>
									</c:forEach>
								
								</table>
							</div>
						</c:otherwise>
						
					</c:choose>
	
				</c:otherwise>
			
			</c:choose>	

	</main>
	
	
	<c:if test="${not empty sessionScope.msg}">
		
		<script>
			alert('${msg}');
		</script>
		
		 <c:remove var="msg" scope="session"/>
	</c:if>
	
	<script src="/resources/js/signup.js"></script>
</body>
</html>