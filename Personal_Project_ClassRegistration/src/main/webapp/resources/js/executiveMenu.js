$(document).ready(function() {
	
	$('#majorMenu').on('click', majorMenu);
	
});

function majorMenu() {
	
	getMajors();
	
}

//Major Menu
function getMajors() {
	$.ajax({
		 url: '/classRegistration/major/getMajors'
		,type: 'get'
		,dataType: 'json'
		,success: function(dat) {
			console.log(dat);
			showList(dat);
			$('div#majorMenu').removeAttr('hidden');
			$('div#mainPage').attr('hidden', 'hidden');
			$('#addBtn').on('click', addMajor);
			
		}
		,error: function(err) {
			console.log(err);
		}
	});
	
	console.log('page load');
}


function addMajor() {
	var addMajor = $('#addMajor').val();
	var addBuilding = $('#addBuilding').val();
	
	$.ajax({
		 url: '/classRegistration/executive/addMajor'
		,type: 'post'
		,data: {
			 major:addMajor
			,building:addBuilding
			}
		,dataType: 'json'
		,success: function(dat) {
			if(dat) {
				getMajors();
			}
			else {
				alert('add error');
			}
		}
		,error: function(err) {
			console.log(err);
		}
		
	});
}

function showList(dat) {
	console.log('showList executed');
	var html = '';
	for ( var d of dat) {
		html += '<tr><td align="center">' + d + '</td>';
		html += '<td>교수:&nbsp;<select major="' + d + '"id="professorList"><select id="lectureList"></select></select></td>';
		html += '</tr>';
		showProfessor(d);
		showLecture(d);
	}
	html += '<tr><td colspan="2" align="center"><input type="text" id="addMajor" placeholder="전공"><input type="text" id="addBuilding" placeholder="강의동"><input type="button" id="addBtn" value="추가"></td></tr>';
	$('#majors').html(html);
	
}

function showProfessor(major) {
	$.ajax({
		 url: '/classRegistration/user/getProfessors'
		,type: 'post'
		,data: {
			major: major
		}
		,dataType: 'json'
		,success: function(dat) {
			var html = '<option value="notSelect">교수</option>';
			for (var d of dat) {					
				html += '<option value="' + d.id +'">' + d.name + '</option>';
			}
			$('#professorList[major="' + major + '"]').html(html);
		}
		,error: function(err) {
			console.log(err);
		}
	});
}

function showLecture(major) {
	
}

