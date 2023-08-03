function toggleReplyWriter(target) {
	const commentItem = $(target).closest(".comment_item");
	const commentWriter = commentItem.children().children().children(".comment_writer");
	if (commentWriter.hasClass("hidden")) {
		commentWriter.removeClass("hidden");
	} else {
		commentWriter.addClass("hidden");
	}
}

function loadComment(category, postId, page) {
	const url = "/community/board/"+ category + "/" + postId +"/comment/list?page=" + page;
	const commentBox = $(".comment_box");
	commentBox.load(url);	
}

function loadReply(target) {
	const url = $(target).attr("url");
	const replyList = $(target).closest(".comment_item").children().children().children(".comment_reply_list");
	
	if ($(target).is("[first]")) {
		$(target).removeAttr("first")
		replyList.load(url);
	} else {
		if (replyList.hasClass("hidden")) {
			replyList.removeClass("hidden");
		} else {
			replyList.addClass("hidden");
		}
	}
}

function addComment(target) {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	const content = $(target).parent().parent().children(".comment_input_area").val().replace(/\n/g, "<br>");
	const url = "/community/board/"+ category + "/" + postId +"/comment/create";

	const commentBox = $(".comment_box");
	commentBox.load(url, {"content" : content});
}

function addReplyComment(target) {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	const content = $(target).parent().parent().children(".comment_input_area").val().replace(/\n/g, "<br>");
	const commentItem = $(target).closest(".comment_item");
	const commentId = commentItem.attr("id");
	const url = "/community/board/"+ category + "/" + postId +"/comment/reply/" + commentId;
	$.ajax({
		type : 'post',
		url : url,
		data: {"content" : content},
		dataType: 'html',
		success : function(data) {
			const replyCommentList = commentItem.children().children().children(".comment_reply_list");
			replyCommentList.append(data);
			$(target).closest(".comment_writer").addClass("hidden");
			const total = $(".comment_header").children();
			total.text(Number(total.text()) + 1);
		}
	});
}

function switchCommentToEditor(target) {
	const content = $(target).closest(".comment").children(".comment_content").text();

	const commentItem = $(target).closest(".comment_item");
	const commentEditor = getEditorFromCommentItem(commentItem);
	const textarea = commentEditor.children(".comment_input_area");	
	textarea.val(content);

	const comment = $(target).closest(".comment");
	comment.addClass("hidden");
	commentEditor.removeClass("hidden");
}

function cancelEditComment(target) {
	const commentItem = $(target).closest(".comment_item");
	const commentEditor = getEditorFromCommentItem(commentItem);
	const comment = getCommentFromCommentItem(commentItem);
	comment.removeClass("hidden");
	commentEditor.addClass("hidden");
}

function editComment(target) {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	const commentItem = $(target).closest(".comment_item");
	const commentId = commentItem.attr("id");

	const commentEditor = getEditorFromCommentItem(commentItem);
	const textarea = commentEditor.children(".comment_input_area");
	const content = textarea.val(); 

	const comment = getCommentFromCommentItem(commentItem);
	const url = "/community/board/"+ category + "/" + postId +"/comment/update/" + commentId;

	$.ajax({
		type : 'post',
		url : url,
		data: {"content" : content},
		dataType: 'html',
		success : function(data) {
			comment.replaceWith(data);
			comment.removeClass("hidden");
			commentEditor.addClass("hidden");
		},
		error:function(request, error) {
			// error 발생 이유
			console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

function deleteComment(target) {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	const commentItem = $(target).closest(".comment_item");
	const commentId = commentItem.attr("id");
	const url = "/community/board/"+ category + "/" + postId +"/comment/delete/" + commentId;
	console.log("delete url : " + url);

	$.ajax({
		type : 'post',
		url : url,
		success : function() {
			console.log("delete");
			const commentWrap = commentItem.children().children(".comment_wrap");
			const comment = commentWrap.children(".comment");
			comment.html('<div class="comment_content">삭제된 댓글입니다</div');

			commentWrap.remove(".comment_editor");
			commentWrap.remove(".comment_writer");
		}
	})
}

function getEditorFromCommentItem(commentItem) {
	return commentItem.children().children().children(".comment_editor");
}

function getCommentFromCommentItem(commentItem) {
	return comment = commentItem.children().children().children(".comment");
}

function count(button, column) {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	const commentItem = $(button).closest(".comment_item");
	const commentId = commentItem.attr("id");

	const url = "/community/board/"+ category + "/" + postId +"/comment/count";

	$.ajax({
		type : 'post',
		url : url,
		dataType : 'text',
		data: {"column" : column, "commentId" : commentId},
		success : function(data) {
			const count = $(button).next();
			count.text(data);
		}
	});
}

$(window).on('load', function() {
	const category = $('#category').text();
	const postId = $('#post_id').text();
	loadComment(category, postId, 1);
})
