<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/resources/js/jquery-3.2.1.js' />"></script>
<style type="text/css">
	@import "/classRegistration/resources/css/join_logIn.css";
</style>
<script type="text/javascript" src="/classRegistration/resources/js/join.js"></script>

<title>회원 가입</title>

	
</head>
<body>

	<h2>ようこそ！　KITA UNIVERSITY에 온 것을 환영합니다!</h2>

	<form id="joinForm">
		<table>
			<tr>
				<td class="key"> ID </td>
				<td class="content"><input type="text" id="id" placeholder="ID 입력" valid="false">  <span id="chkID"></span></td>
			</tr>
			<tr>
				<td class="key" rowspan="2"> PW </td>
				<td class="content"><input type="password" id="pw" placeholder="PW 입력" valid="false"> <span id="chkPW"></span></td>
			</tr>
			<tr>
				<td class="content"><input type="password" id="pw2" placeholder="PW 확인"> <span id="chkPW2"></span></td>				
			</tr>
			<tr>
				<td class="key"> 이름 </td>
				<td class="content"><input type="text" id="name" placeholder="이름 입력" valid="false"></td>
			</tr>
			<tr>
				<td class="key"> 전공 </td>
				<td class="content" id="divMajor">
					<input class="" type="radio" name="job" id="student" value="student">학생 
					<input class="" type="radio" name="job" id="professor" value="professor">교수 
					<!-- AJAX를 사용하여 등록되어있는 전공 학부를 불러와 스크롤로 표시 -->
					<select id="majors" valid="false">
						
					</select>
					<!-- 학생일 경우 학년 표시 -->
					<span id="grade"></span>
				</td>
			</tr>
			<tr id="tutor">
			<!-- 학생일 경우 지도교수를 스크롤로 표시 (전공학부에 등록된 교수) -->
				
			</tr>
			
			
		</table>
		<input type="button" id="formCheck" value="가입하기">
	</form>
		<br><a href="../">뒤로가기</a>


</body>
</html>