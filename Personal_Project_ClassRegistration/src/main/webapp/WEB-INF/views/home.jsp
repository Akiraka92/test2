<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2>
	KITA University 수강신청
</h2>

	<ul>
	
		<c:if test="${user==null}">	
			<li><a href="user/signUp"> 회원 등록 </a></li>
			<li><a href="user/logIn"> 로그인 </a></li>
		</c:if>
		<c:if test="${user!=null}">	
			<li><a href="main"> 수강 신청 </a></li>
			<li><a href="user/logOut"> 로그아웃 </a></li>
		</c:if>
		
		
		
	</ul>


</body>
</html>
