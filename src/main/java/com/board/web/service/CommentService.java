package com.board.web.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.web.controller.comment.CommentForm;
import com.board.web.entity.Comment;
import com.board.web.entity.Member;
import com.board.web.repository.CommentRepository;

@Transactional
@Service
public class CommentService {
	
	private final DataSource dataSource;
	private final CommentRepository commentRepository;
	
	public CommentService(DataSource dataSource, CommentRepository commentRepository) {
		this.dataSource = dataSource;
		this.commentRepository = commentRepository;
	}

	public int createComment(Member member, CommentForm commentForm) {
		return commentRepository.insertComment(member, commentForm);
	}
	
	public List<Comment> findComments(String category, Long postId, int number) {
		return commentRepository.findComments(category, postId, number);
	}
	
	public int updateContent(Member member, String category, Long commentId, String content) {
		int result = 0;
		if (isMyComment(member, category, commentId)) {
			result = commentRepository.updateContent(category, commentId, content);
		}
		
		return result;
	}
	
	public int updateCount(String category, String column, Long commentId) {
		commentRepository.updateCount(category, column, commentId);
		int count = commentRepository.findCount(category, column, commentId);
		return count;
	}
	
	public int deleteComment(Member member, String category, Long commentId) {
		int result = 0;
		if (isMyComment(member, category, commentId)) {
			result = commentRepository.deleteComment(category, commentId);
		}
		
		return result;
	}
	
	private boolean isMyComment(Member member, String category, Long commentId) {
		Comment comment = commentRepository.findOne(category, commentId);
		if (comment.getMemberId().equals(member.getId())) {
			return true;
		} else {
			return false;
		}
	}
}
