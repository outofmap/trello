var TODO = (function (window){

	 'use strict';

	 var board_btn = " <li class='board waves-effect waves-light btn'>" +		
	 						"{{input-value}}" +
	 					"</li>"

	function init(){

		$("#boards_list").on("click", ".board", page_nav);
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
		var str = board_btn.replace(/\{\{input-value\}\}/gi,project_name);
		$(".add_project").before(str);
		$("#add_project").val("");
		$(".add_project_form").css('display','none');
		$(".btn-floating").css('display','block');
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