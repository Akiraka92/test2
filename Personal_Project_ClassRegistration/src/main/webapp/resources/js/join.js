$(document).ready(function() {
			
	// 폼 체크
	$('#formCheck').on('click', formCheck);
	
	$('#name').on('blur', function() {
		name = $(this).val();
		if(name!='') {
			$('#name').attr('valid', 'true');
		}
		else {
			$('#name').attr('valid', 'false');
		}
		console.log('nameValid : ' + $('#name').attr('valid'));
	});
	
	// ID check 중복확인
	$('#id').on('blur', function() {
		var id = $(this).val();
		if(id.length>20 || id.length<4) {
			$('#chkID').css('color', 'red');
			$('#chkID').text('4~20자리로 입력해주세요.');
		}
		else {
			idCheck(id);
			
		}
		
	});
	
	
	// PW 입력 확인
	$('#pw').on('blur', PWcheck1);
	
	$('#pw, #pw2').on('blur', PWcheck2);
	
	
	// 전공 스크롤 불러오기
	getMajors();
	
	
	// 학년, 지도교수 체크
	$('#divMajor, #tutor').on('change', checkJob);
		// #student, #professor, #majors
	
});


function idCheck(id) {
	$.ajax({
		 url: 'idCheck'
		,type: 'post'
		,data: {id: id}
		,dataType: 'json'
		,success: function(data) {
			console.log(data);
			
			// call back hell!!!!
			idQuery(data);
		}
		,error: function(err) {
			console.log(err);
		}
	});
	// alert('id check');
}

function idQuery(data) {
	if(data) {
		$('#chkID').css('color', 'red');
		$('#chkID').text('이미 존재하는 ID입니다.');
		$('#id').attr('valid', 'false');
	}
	else if(!data) {
		$('#chkID').css('color', 'black');
		$('#chkID').text('사용할 수 있는 ID입니다.');
		$('#id').attr('valid', 'true');
	}
	else {
		alert(checkID + '아이디 중복체크 에러');
		$('#id').attr('valid', 'false');
		
	}
	console.log('idValid : ' + $('#id').attr('valid'));
}


function formCheck() {
	
	var job = $('#majors').attr('valid');
	var grade = $('#gradeSelect option:selected').attr('value');
	if(grade == null) {
		grade = 0;
	}
	
	
	
	
	if($('#id').attr('valid')=='true') {
		console.log('ID confirm');
	}
	else {
		$('#id').val('');
		$('#id').focus();
		alert("ID를 다시 입력하세요.");
		return;
	}
	
	if($('#pw').attr('valid')=='true') {
		console.log('PW confirm');
	}
	else {
		$('#pw').val('');
		$('#pw2').val('');
		$('#pw').focus();
		alert("PW를 다시 입력하세요.");
		return;
	}
	if($('#name').attr('valid')=='true') {
		console.log('Name confirm');
	}
	else {
		$('#name').val('');
		$('#name').focus();
		alert("이름을 다시 입력하세요.");
		return;
	}
	
	if(job=='professor' || job=='student') {
		console.log('ID confirm');
	}
	else {
		alert("학적사항을 다시 입력하세요.");
		return;
	}
	
	
	
	// AJAX로 서버에 전송
	$.ajax({
		 url: 'signUp'
		,type: 'post'
		,data: {
			 id: $('#id').val()
			,pw: $('#pw').val()
			,name: $('#name').val()
			,job: job
			,major: $('#majors option:selected').attr('value')
			,grade: grade
			,tutor: $('#tutor option:selected').attr('value')
		}
		,dataType: 'json'
		,success: function(dat) {
			if(dat) {						
				location.href='../';
			}
			else {
				alert('비정상적인 접근입니다!!');
			}
		}
		,error: function(err) {
			console.log(err);
		}
	});
	// alert('Form check executed!!');
	
	
}



// PW Check!!!!!!!!!!!!!!!!!!!
function PWcheck1() {
	var pw = $('#pw').val();
	if(pw == '') {
		$('#chkPW').text('');
	}
	else if(pw.length > 20 || pw.length < 4) {
		$('#chkPW').css('color', 'red');
		$('#chkPW').text('4~20자리로 입력해주세요.');
	}
	else {
		$('#chkPW').text('');
	}
}
function PWcheck2() {
	var pw2 = $('#pw2').val();
	if(pw2 == '') {
		$('#chkPW2').text('');
		$('#pw').attr('valid', 'false');
	}
	else if(pw2 == $('#pw').val()) {
		$('#chkPW2').css('color', 'black');
		$('#chkPW2').text('PW가 일치합니다.');
		$('#pw').attr('valid', 'true');
	}
	else {
		$('#chkPW2').css('color', 'red');
		$('#chkPW2').text('PW가 일치하지 않습니다.');
		$('#pw').attr('valid', 'false');
	}
	console.log('passwordValid : ' + $('#pw').attr('valid'));
}


// 전공 목록
function getMajors() {
	$.ajax({
		 url: '/classRegistration/major/getMajors'
		,type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			console.log(dat);
			majorsList(dat);
		}
		,error: function(err) {
			console.log(err);
		}
	});
}
function majorsList(dat) {
	var html = '<option value="notSelect">학부</option>';
	for ( var d of dat) {
		html += '<option value="' + d + '">' + d + '</option>';
	}
	$('#majors').html(html);
}


// 
function checkJob() {
	var major = $('#majors option:selected').attr('value');
	console.log(major);
	console.log('gradeSelect : '+ $('#gradeSelect option:selected').prop('value'));
	var html = '';
	if($('#student').prop('checked') && major!='notSelect') {
		if($('#gradeSelect option:selected').prop('value')===undefined) {
			html += '학년 : <select id="gradeSelect"><option value="notSelect">선택</option>';
			for (var i = 1; i <= 5; i++) {					
				html += '<option value="' + i + '">' + i + '</option>';
			}
			html += '</select>';
			$('#grade').html(html);
		}
		if($('#gradeSelect option:selected').attr('value')!='notSelect') {

			$.ajax({
				 url: 'getProfessors'
				,type: 'post'
				,data: {
					major: major
				}
				,dataType: 'json'
				,success: function(dat) {
					html = '<td class="key">지도교수</td>';
					html += '<td class="content"><select id="tutorSelect">';
					for (var d of dat) {					
						html += '<option value="' + d.id +'">' + d.name + '</option>';
					}
					html += '</select></td>';
					$('#tutor').html(html);
				}
				,error: function(err) {
					console.log(err);
				}
			});
			
			
		}
		else {
			html = '';
			$('#tutor').html(html);
		}
		
		
		// valid check
		var gradeCheck = $('#gradeSelect option:selected').attr('value');
		if(gradeCheck != 'notSelect') {
			$('#majors').attr('valid', 'student');
		}
		else {
			$('#majors').attr('valid', 'false');
		}
		
		// test
		console.log('넌 학생이구!!');
	}
	else  if($('#professor').prop('checked') && major!='notSelect') {
		html = '';
		$('#grade').html(html);
		$('#tutor').html(html);
		console.log('난 선생이야!!');
		$('#majors').attr('valid', 'professor');
		
	}
	else {
		html = '';
		$('#grade').html(html);
		$('#tutor').html(html);
		$('#majors').attr('valid', 'false');
	}
	
	// 전공, 학적 사항 검증
	console.log('majorValid : ' + $('#majors').attr('valid'));
}


