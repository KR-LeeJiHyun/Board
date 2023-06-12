package com.board.web.controller.comment;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.entity.Comment;
import com.board.web.service.CommentService;

@Controller
@RequestMapping("/board/{category}/{postId}/comment/")
public class CommentController {
	
	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<Comment> list(@PathVariable String category, Long postId, int page) {
		List<Comment> list = commentService.findComments(category, postId, page);
		return list;
	}

	@ResponseBody
	@PostMapping("/count")
	public int like(@PathVariable String category, String column, Long commentId) {
		return commentService.updateCount(category, column.toUpperCase(), commentId);
	}	
}
