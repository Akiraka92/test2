$(document).ready(function() {
	
	$('#submit').on('click', formCheck);
	// $('#logInForm').on('submit', formCheck);
	
});

function formCheck() {
	
	var id = $('#id').val();
	var pw = $('#pw').val();
	if(id == '') {
		alert('ID를 입력하세요.');
		$('#id').focus();
		return;
	}
	if(pw == '') {
		alert('PW를 입력하세요.');
		$('#pw').focus();
		return;
	}
	
	
	console.log('ID:' + id + ', PW:' + pw);
	
	$.ajax({
		 url: 'logIn'
		,type: 'post'
		,dataType: 'json'
		,data: {
			 id: id
			,pw: pw
		}
		,success: function(dat) {
			console.log('logIn ID : ' + dat.id);
			if(dat.id==null) {
				alert('회원정보가 잘못되었습니다.');
				$('#id').val('');
				$('#pw').val('');
				$('#id').focus();
			}
			else {
				
				location.href='../main';
			}
		}
		,error: function(err) {
			alert('ERROR');
			console.log(err);
		}
	});
	
	
}


function notWork() {
	return false;
}



