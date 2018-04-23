<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<body>
	<br>
	<h2 align="center">KITA대학교</h2>
	<br>
	<ul>
		<li>
			${user.name}
			<c:if test="${user.job=='student'}">
				학우
			</c:if>
			<c:if test="${user.job=='professor'}">
				교수
			</c:if>
			님 안녕하세요. &nbsp;<a href="user/logOut">로그아웃</a>
		</li>
	</ul>
	<ul>
		<li>
			${user.major}
			<c:if test="${user.job=='student'}">
				&nbsp;${user.grade}학년
			</c:if>
		
		</li>
	</ul>
	<ul>
		
			
		<c:if test="${user.job=='professor'}">
			<li>
				<c:if test="${user.major!='executive'}">
					<input type="button" id="lectureEdit" value="과목 관리">
				</c:if>
				<c:if test="${user.major=='executive'}">
					<input type="button" id="majorMenu" value="전공 관리">
				</c:if>
			</li>
		</c:if>
			<c:if test="${user.job=='student'}">
				<li>
					<input type="text" id="searchSubject" placeholder="과목명 검색">
					<input type="button" id="searchSubjectBtn" value="강의 검색">
				</li>
			</c:if>
		<li>			
			<input type="button" id="allClass" value="전체 강의">
			<c:if test="${user.major!='executive'}">
				&nbsp;<input type="button" id="myList" value="내 강의">
			</c:if>
		</li>
	
	</ul>
	
	<input type="button" id="divTest" value="test" hidden="hidden">
	
</body>
