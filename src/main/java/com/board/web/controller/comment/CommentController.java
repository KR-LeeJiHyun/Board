package com.board.web.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.service.CommentService;

@Controller
@RequestMapping("/board/{category}/{postId}/comment/")
public class CommentController {
	
	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@ResponseBody
	@PostMapping("/count")
	public int like(@PathVariable String category, String column, Long commentId) {
		return commentService.updateCount(category, column.toUpperCase(), commentId);
	}	
}
