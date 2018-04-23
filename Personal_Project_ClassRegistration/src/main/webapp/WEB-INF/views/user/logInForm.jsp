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
<script type="text/javascript" src="/classRegistration/resources/js/logIn.js"></script>

<title>로그인</title>



</head>
<body>

	<form id="logInForm" onsubmit="return notWork()">	
		<table>
			<tr>
				<td class="key">
					ID
				</td>
				<td class="content2">
					<input type="text" id="id">
				</td>
			</tr>
			<tr>
				<td class="key">
					PW
				</td>
				<td class="content2">
					<input type="password" id="pw">
				</td>
			</tr>
		</table>
		<input type="submit" id="submit" value="로그인">
	</form>
	<br><a href="../">뒤로가기</a>



</body>
</html>