<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/resources/js/jquery-3.2.1.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	
	var subNum = opener.document.getElementById('addNum').value;
	$(document).ready(function() {
		
		showSubject(subNum);
		
		
	});

	
	function showSubject(subNum) {
		console.log('1');
		$.ajax({
			 url: 'getLecture'
			,type: 'post'
			,data: {lectureId:subNum}
			,dataType: 'json'
			,success: function(dat) {
				var html = '';
				html += dat.major + dat.subject + '&nbsp;' + dat.credit + '학점(시수)<br>';
				$('#addClassPopUp').html(html);
				showSelect(dat.credit);
			}
			,error: function(err) {
				console.log(err);
			}
		});
	}
	
	function showSelect(credit) {
		var html = '<select id="daySelect"><option value="mon">월</option><option value="tue">화</option><option value="wed">수</option><option value="thu">목</option><option value="fri">금</option></select>';
		html += '요일&nbsp;';
		html += '<select id="scheduleSelect">';
		for (var i = 1; i <= 13-credit; i++) {
			html += '<option value="'+ i + '">' + i + '</option>';
		}
		html += '</select>교시부터<br>';
		html += '정원:&nbsp;<select id="quota">';
		for (var i = 20; i <= 50; i+=5) {
			html += '<option value="'+ i + '">' + i + '</option>';
		}
		html += '</select><br>';
		html += '<input type="button" id="addClassSubmit" value="등록">';
		$('#addClassPopUp').html($('#addClassPopUp').html() + html);
		
		$('#addClassSubmit').on('click', addClassSubmit);
		
	}
	
	
	
	function addClassSubmit() {
		var schedule_day = $('#daySelect option:selected').attr('value');
		var schedule_start = $('#scheduleSelect option:selected').attr('value');
		var quota = $('#quota option:selected').attr('value');
		var lectureId = subNum;
		
		$.ajax({
			 url: 'insertClass'
			,type: 'post'
			,data: {
				 schedule_day:schedule_day
				,schedule_start:schedule_start
				,quota:quota
				,lectureId:lectureId
			}
			,dataType: 'json'
			,success: function(dat) {
				if(dat) {
					alert('등록 완료');
					$(opener.document.getElementById('addExecuted')).trigger('click');
					window.close();
					// opener.document.getElementById('');
				}
				else {
					alert('등록 실패');
				}
			}
			,error: function(err) {
				console.log(err);
			}
		});
	}


</script>
</head>
<body>

<div id="addClassPopUp"></div>



</body>
</html>
