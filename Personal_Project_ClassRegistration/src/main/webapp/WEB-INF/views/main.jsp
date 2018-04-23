<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<c:url value='/resources/js/jquery-3.2.1.js' />"></script>
<style type="text/css">
	@import "/classRegistration/resources/css/classList.css";
</style>
<script type="text/javascript" src="/classRegistration/resources/js/executiveMenu.js"></script>
<script type="text/javascript" src="/classRegistration/resources/js/professorMenu.js"></script>
<script type="text/javascript" src="/classRegistration/resources/js/commonMenu.js"></script>
<script type="text/javascript" src="/classRegistration/resources/js/studentMenu.js"></script>

<title>수강신청</title>
</head>
<body>

	<%-- <div id="info_menu">
		<%@ include file="menu.jsp" %>
		<jsp:include page="menu.jsp"></jsp:include>
	</div> --%>
	
	<!-- <div class="content" id="mainPage">		 -->
		<!-- <br>
		<h2 id="notice" align="center">왼쪽 메뉴에서 항목을 선택하세요.</h2>
		 -->
		<div id="classDiv" hidden="hidden">
			<jsp:include page="class/lectureList.jsp"></jsp:include>
		</div>
		
		<div id="myDiv" hidden="hidden">
			<jsp:include page="class/myClass.jsp"></jsp:include>
		</div>
		
	<!-- </div> -->
	
	<div class="content" id="majorMenu" hidden="hidden"><jsp:include page="executive/majorMenu.jsp"></jsp:include></div>

</body>
</html>

