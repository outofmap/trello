var TODO = (function (window){

	 'use strict';

	var list_html = "<div class='list_wrapper'>" +
            "<div class='list_content z-depth-1'>" +
                "<div class='list_header'>"+
                 "<textarea class='list_header_name'>{{input-value}}</textarea>"+
               "</div>" +
                "<div class='list_cards'></div>" +
				"<div class='card_composer'>" +
                    "<div class='add_card_form'>" +
                      "<textarea class='list_card_composer_textarea'></textarea>" +
                       "<a class='waves-effect  waves-light btn card_save blue-grey lighten-5'>save</a>" +
                       "<a class='waves-effect waves-light btn card_cancel blue-grey lighten-5'>cancel</a>" +
                    "</div>" +
                    "<a class='add_card' href='#''>Add a Card...</a>" +
                "</div>" +
            "</div>" +
          "</div>"

    var list_template = Handlebars.compile(list_html);

    var card_html = "<div class='list_card'>" +
      						"<div class='list_card_detail'>" +
                      	 		"<a class='list_card_title modal-trigger modalLink' dir='auto' href='#modalLayer' >{{value}}</a>" +
                     	 	"</div>" +
                      "</div>";

    var card_template = Handlebars.compile(card_html);

    var comment_html =   "<div class='comment'>" +
			                    "<div class='commenter'>{writer_name}</div>" +
			                    "<div class='comment_contents z-depth-1'>{{comment_contents}}</div>" +
			                    "<div class='comment_date'>{{current_time}} - </div>" +
			                    "<div class='comment_reply'> Reply</div>" +
              			  "</div>";

    var comment_template = Handlebars.compile(comment_html);       			             

	function init(){

  		$("#board_canvas").on("click", ".modalLink", show_modal);
		$(".btn-floating").on("click", create_list);
		$(".save").on("click", add_list);
		$("#board_canvas").on("click",".add_card", add_card);
		$("#board_canvas").on("click",".card_save", card_save);
		$("#board_canvas").on("click",".card_cancel", card_cancel);
   		$( "#sortable" ).disableSelection();
		$(".add_list a.cancel").on("click", cancel);
		$(".add_list").removeClass("ui-sortable-handle");
   		$(".attach_from_computer").on("click", file_upload);
   		$(".comment_send").on("click", add_comment);
   		$( "#sortable" ).sortable({
    		  placeholder: "ui-state-highlight",
    		  cancel: ".add_list"
   		});
   		$( "#board_canvas" ).sortable();
  		$( "#board_canvas" ).disableSelection();
   		$(".members_btn").on("click", search_member);
   		$(".due_date_btn").on("click", setting_date);
   		$(".file_attachment").on("click", setting_attachment);
	    $(".datepicker").pickadate({
			    selectMonths: true, 
			    selectYears: 15 
	  	});
	  	$(".close_button").on("click", close_modal);
	  	$(".shadow_body").on("click", close_modal);
	  	$('.modal-trigger').leanModal();
	}

	function close_modal(){

		$("#modalLayer").fadeOut("slow");
		$(".shadow_body").fadeOut("slow");
	}

	function setting_attachment(){

		if($(".modal_for_attachment").hasClass("clicked")){
			$(".modal_for_attachment").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_attachment").addClass("clicked").slideDown();
	}

	function setting_date(){

		if($(".modal_for_due_date").hasClass("clicked")){
			$(".modal_for_due_date").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_due_date").addClass("clicked").slideDown();
		
	}

	function search_member(){

		console.log("asd");
		if($(".modal_for_members").hasClass("clicked")){
			$(".modal_for_members").removeClass("clicked").slideUp();
			return;
		}

		$(".modal_for_members").addClass("clicked").slideDown();
	}

	function add_comment(e){
	
		var comment_contents = $(".comment_contents").val();
		var now = new Date();
		var currentTime = now.getDate() + " " +
					  month_written_english(now.getMonth()+1) + " " +
					  now.getFullYear() + " at " +
					  now.getHours() + ":" +
					  now.getMinutes();
		$(comment_template({"comment_contents":comment_contents, "current_time":currentTime})).appendTo(".comments");
		$(".comment_contents").val("");


	}

	function month_written_english(month){

		if(month === 1){
			return "Jan";
		}else if(month === 2){
			return "Feb";
		}else if(month === 3){
			return "Mar";
		}else if(month === 4){
			return "Apr";
		}else if(month === 5){
			return "May";
		}else if(month === 6){
			return "Jun";
		}else if(month === 7){
			return "July";
		}else if(month === 8){
			return "Aug";
		}else if(month === 9){
			return "Sep";
		}else if(month === 10){
			return "Oct";
		}else if(month === 11){
			return "Nov";
		}else if(month === 12){
			return "Dec";
		}
	}

	function file_upload(){
		$("#fileUpload").trigger("click");
	}

	function show_modal(e){
		$(".shadow_body").fadeIn("slow");
		$("#modalLayer").fadeIn("slow");
		var title = $(e.target).text();
		$(".card_title_in_modal").text(title);
		var list_name = $(e.target).closest(".list_content").find(".list_header_name").val();
		$(".list_name").text(list_name);
	}

	function card_cancel(e){

		$(e.target).closest(".card_composer .add_card_form").css('display', 'none');
		$(e.target).closest(".card_composer").find("a.add_card").css('display', 'block');
	}

	function cancel(){

		$(".btn-floating").css('display','block');
		$(".add_list_form").css('display','none');

	}

	function modal(){
		$('.modal-trigger').leanModal();
	}

	function create_list(){

		$(".btn-floating").css('display','none');
		$(".add_list_form").css('display','block');
	}

	function add_card(e){
		// $(this).closest(".card_composer").find()
		$(e.target).parent().find(".add_card_form").css('display', 'block');
		$(e.target).parent().find("a.add_card").css('display', 'none');
	}
	function card_save(e){

		$(".add_card_form").css('display', 'none');
		var card_text = $(e.target).parent(".add_card_form").find(".list_card_composer_textarea").val();
		var $list_wrapper = $(e.target).closest(".list_wrapper");
		var str = card_template({"value":card_text});
		$list_wrapper.find(".list_cards").append(str);
		$(e.target).parent(".add_card_form").find(".list_card_composer_textarea").val("");
		$(e.target).parents(".card_composer").find("a.add_card").css('display', 'block');

	}
	
	function add_list(){

		var list_name = $("#add_list").val();
		var str = list_html.replace(/\{\{input-value\}\}/gi,list_name);
		$(".add_list").before(str);
		$("#add_list").val("");
		$(".add_list_form").css('display','none');
		$(".btn-floating").css('display','block');
	}

	return {
		"init" : init
	}
})(window);

$(function(){
    TODO.init();
});