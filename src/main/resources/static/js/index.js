var TODO = (function (window){

	 'use strict';

	 var board_btn = "<a href=/boards/"+"{{boardId}}"+">"+ "<li class='board waves-effect waves-light btn'>" +		
	 						"{{input-value}}" +
	 					"</li>"+"</a>";
	var baseUrl = "http://localhost:8080";
	 
	function init(){

		//$("#boards_list").on("click", ".board", page_nav);
		$("#create_board").on("click", create_board);
		$(".add_project_btn").on("click", create_new_project);
		$(".save").on("click", add_project);
		$(".add_project a.cancel").on("click", cancel);
	}

	function cancel(){

		$(".btn-floating").css('display','block');
		$(".add_project_form").css('display','none');

	}

	function add_project(){
		
		var project_name = $("#add_project").val();
		var str = board_btn.replace(/\{\{input-value\}\}/gi, project_name);
		var data = JSON.stringify({name:project_name});
		console.log("data:"+data);
		$.ajax({
		    contentType : "application/json; charset=UTF-8",
			url: baseUrl+"/boards",
			data: data,
			dataType:'json',
			type:"post"
		}).done(function (data, status) {
			console.log(data);
			console.log(data.boardId);
			var boardId = data.boardId;
			var newboard = str.replace(/\{\{boardId\}\}/gi,boardId);
			$(".add_project").before(newboard); 				//board추가 UI
			$("#add_project").val("");
			$(".add_project_form").css('display','none');
			$(".btn-floating").css('display','block');
		}).fail(function (data,status){
			console.log("failed");
		})
	}

	function create_new_project(){

		$(".add_project_btn").css('display','none');
		$(".add_project_form").css('display','block');
	}

	function page_nav(){

		window.location.href = ("page.html");
	}

	function create_board(){
		$("#boards_list").prepend(board_btn);
	}
	

	return {
		"init" : init
	}
})(window);

$(function(){
    TODO.init();
});