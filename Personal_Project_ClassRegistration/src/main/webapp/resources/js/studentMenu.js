
$(document).ready(function() {

	// studentCheck();
	
});

function applyClass() {
	var classNo = $(this).attr('cls_no');

	$.ajax({
		 url: 'class/applyClass'
		,type: 'post'
		,data: {class_no : classNo}
		,dataType: 'json'
		,success: function(dat) {
			if(dat) {
				allClass();
			}
			else {
				alert('등록할 수 없습니다.');
			}
		}
		,error: function(err) {
			console.log(err);
		}
		
	});
}


function studentCheck() {
	$.ajax({
		url: 'user/getUser'
		,type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			if(dat.job=='student') {
				$('#apllyBtnTd').removeAttr('hidden');
			}
			else {
				$('#apllyBtnTd').attr('hidden', 'hidden');
			}
		}
		,error: function(err) {
			console.log(err);
		}
	});
	
}
