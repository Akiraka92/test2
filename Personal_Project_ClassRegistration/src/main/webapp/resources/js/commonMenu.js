
$(document).ready(function() {
	$('#allClass').on('click', allClass);
	$('#myList').on('click', myClass);
	$('#searchSubjectBtn').on('click', searchClass);
	
});



function allClass() {
	
	$.ajax({
		 url: 'class/allClass'
		,type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			console.log('allClass');
			showAllClass(dat);
			if($('#classDiv').prop('hidden')) {
				$('#classDiv').removeAttr('hidden');
				$('#classDiv').html('<h2 align="center">강의 목록</h2> ' + $('#classDiv').html());				
			}
			else {
				$('#classDiv>h2').text('강의 목록');
			}
			
		}
		,error: function(err) {
			console.log(err);
		}
	});
}


function showAllClass(dat) {
	var html = '<tr><td>강의번호</td><td>과목번호</td><td>과목명</td><td>전공</td><td>교수</td><td>이수학점</td><td>강의시간</td><td>정원</td></tr>';
	for ( var d of dat.list) {
		html += '<tr><td>' + d.class_no + '</td><td>' + d.lecture_id + '</td><td>'+ d.lecture_name + '</td><td>' + d.lecture_major + '</td><td>' + d.professor_name + '</td><td>' + d.credit + '</td><td>' + d.schedule_day + '&nbsp;' + d.schedule_start + '~' + d.schedule_end + '</td><td>' + d.applicant + '/' + d.quota + '</td>';
		if(dat.user.job == 'student') {
			html += '<td id="apllyBtnTd"><input type="button" class="applyBtn" value="수강신청" cls_no="' + d.class_no + '"></td>';
		}
		html += '</tr>';
		
	}
	$('#lectureList').html(html);
	
	myClass();
	
}


function myClass() {
	console.log('myClass');
	$.ajax({
		 url: 'class/myClass'
		,type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			showMyClass(dat);
			$('div#myDiv').removeAttr('hidden');
			$('.applyBtn').on('click', applyClass);
		}
		,error: function(err) {
			console.log(err);
		}
	});
	
}


function showMyClass(dat) {
	var html = '<tr>';
	html += '<tr><td>강의번호</td><td>과목번호</td><td>과목명</td><td>전공</td><td>교수</td><td>이수학점</td><td>강의시간</td><td>정원</td></tr>';
	for ( var d of dat) {
		html += '<tr><td>' + d.class_no + '</td><td>' + d.lecture_id + '</td><td>'+ d.lecture_name + '</td><td>' + d.lecture_major + '</td><td>' + d.professor_name + '</td><td>' + d.credit + '</td><td>' + d.schedule_day + '&nbsp;' + d.schedule_start + '~' + d.schedule_end + '</td><td>' + d.applicant + '/' + d.quota + '</td>';
		html += '<td><input type="button" class="deleteClass" deleteClassNo="' + d.class_no + '" value="삭제"></td>';
		html += '</tr>';
	}
	$('#myClass').html(html);
	console.log('myClass');
	$('.deleteClass').on('click', deleteCheck);
	
}

function deleteCheck() {
	var classNo = $(this).attr('deleteClassNo');
	
	$.ajax({
		 url: 'user/getUser'
		,Type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			if(dat.job=='professor') {
				var delChk = confirm('강의 삭제시,\n해당 강의에 대한 모든 수강신청이 취소됩니다.');
				if(delChk) {
					deleteClass(classNo);					
				}
			}
			else if(dat.job=='student') {
				var delChk = confirm('강의를 취소하겠습니까?');
				if(delChk) {
					deleteClass(classNo);					
				}
			}
		}
		,error: function(err) {
			console.log(err);
		}
	});
	
}


function deleteClass(classNo) {
	$.ajax({
		 url: 'class/deleteClass'
		,type: 'post'
		,data: {class_no:classNo}
		,dataType: 'json'
		,success: function(dat) {
			if(dat) {
				allClass();
			}
			else {
				alert('삭제 ERROR');
			}
		}
		,error: function(err) {
			console.log(err);
		}
	});
	
}


function searchClass() {
	var searchSubject = $('#searchSubject').val();
	if(searchSubject!='') {
		$.ajax({
			 url: 'class/searchClass'
			,type: 'post'
			,data: {searchSubject:searchSubject}
			,dataType: 'json'
			,success: function(dat) {
				showSearchClass(dat);
				if($('#classDiv').prop('hidden')) {
					$('#classDiv').removeAttr('hidden');
					$('#classDiv').html('<h2 align="center">검색 목록</h2> ' + $('#classDiv').html());				
				}
				else {
					$('#classDiv>h2').text('검색 목록');
				}
			}
			,error: function(err) {
				console.log(err);
			}
		});
	}
	else {
		alert('키워드를 입력하세요!');
	}
}


function showSearchClass(dat) {
	var html = '<tr><td>강의번호</td><td>과목번호</td><td>과목명</td><td>전공</td><td>교수</td><td>이수학점</td><td>강의시간</td><td>정원</td></tr>';
	for ( var d of dat) {
		html += '<tr><td>' + d.class_no + '</td><td>' + d.lecture_id + '</td><td>'+ d.lecture_name + '</td><td>' + d.lecture_major + '</td><td>' + d.professor_name + '</td><td>' + d.credit + '</td><td>' + d.schedule_day + '&nbsp;' + d.schedule_start + '~' + d.schedule_end + '</td><td>' + d.applicant + '/' + d.quota + '</td>';
		if(dat.job == 'student') {
			html += '<td id="apllyBtnTd"><input type="button" class="applyBtn" value="수강신청" cls_no="' + d.class_no + '"></td>';
		}
		html += '</tr>';
		
	}
	$('#lectureList').html(html);
	
	myClass();
	
}
