package com.board.web.repository;

import java.util.List;

import com.board.web.controller.comment.CommentForm;
import com.board.web.entity.Comment;
import com.board.web.entity.Member;

public interface CommentRepository {
	int insertComment(Member member, CommentForm commentForm);
	Comment findOne(String category, Long commentId);
	List<Comment> findComments(String category, Long postId, int number);
	int findCount(String category, String column, Long commentId);
	int updateCount(String category, String column, Long commentId);
	int updateContent(String category, Long commentId, String content);
	int deleteComment(String category, Long commentId);
}
