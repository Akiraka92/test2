$(document).ready(function() {
	
	// $('#divTest').on('click', setDiv);
	
	$('#lectureEdit').on('click', lectureEdit);
	$('#addExecuted').on('click', myClass);
	
});



function lectureEdit() {
	
	$.ajax({
		 url: 'class/getLectureList'
		,type: 'get'
		// ,data: {major: major}
		,dataType: 'json'
		,success: function(dat) {
			console.log('lectureEdit');
			showLecture(dat);
			if($('#classDiv').prop('hidden')) {
				$('#classDiv').removeAttr('hidden');
				$('#classDiv').html('<h2 align="center">과목 목록</h2> ' + $('#classDiv').html());				
			}
			else {
				$('#classDiv>h2').text('과목 목록');
			}
		}
		,error: function(err) {
			console.log(err);
		}

	});
}


function showLecture(lecture) {
	// var addLecBtn = $('<input type="button" class="addLecture" value="과목 추가">').on('click', addLecture);
	var html = '<tr><td>과목번호</td><td>과목명</td><td>전공</td><td>이수학점</td></tr>';
	for ( var l of lecture) {
		html += '<tr><td>' + l.lectureId + '</td><td>' + l.subject + '</td><td>' + l.major + '</td><td>' + l.credit + '</td>';
		// html += '<td><input type="button" value="강의 추가" class="addClassBtn" subnum="' + l.lectureId + '"></td></tr>';
		html += '<td><a href="javascript:addClass(' + l.lectureId + ')" subnum=""><button>강의 추가</button></a></td></tr>';
		
	}

	html += '<tr><td colspan="5" id="addLecId"><input type="text" id="lecSubject" placeholder="과목명">&nbsp;<input type="text" id="lecCredit" placeholder="이수학점">&nbsp;</td></tr>';
	html += '<input type="hidden" id="addNum" value="">';
	$('#lectureList').html(html);
	
	$('td#addLecId').append('<a href="javascript:addLecture()"><button>과목 추가</button></a>');
	// $('td#addLecId').append(addLecBtn);
	// $('input.addLecture').on('click', addLecture);
	// $('input.addClassBtn').on('click', addClass);
	
	
	myClass();
}


function addLecture() {
	console.log('과목 추가');
	var subject = $('#lecSubject').val();
	var credit = $('#lecCredit').val();
	
	$.ajax({
		url: 'class/addLecture'
		,type: 'post'
		,data: {
			 subject: subject
			,credit: credit
		}
		,dataType: 'json'
		,success: function(dat) {
			console.log('addLecture');
			if(dat) {
				lectureEdit();
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

/*
function addClass() {
	var subjectId = $(this).attr('subnum');
	$('#addNum').attr('value', subjectId);
	window.open("class/addClass", "강의 추가", "top=200, left=400, width=400, height=250");
	
}
*/

function addClass(subjectId) {
	$('#addNum').attr('value', subjectId);
	window.open("class/addClass", "강의 추가", "top=200, left=400, width=400, height=250");
	
}

