package com.board.web.repository;

import java.util.List;

import com.board.web.controller.comment.CommentForm;
import com.board.web.entity.Comment;
import com.board.web.entity.Member;
import com.board.web.repository.jdbc.comment.CommentDTO;

public interface CommentRepository {
	Long insertComment(Member member, CommentForm commentForm);
	Comment findOne(String category, Long commentId);
	List<CommentDTO> findComments(String category, Long postId, int page);
	List<CommentDTO> findReplyComments(String category, Long postId, Long parentId);
	Long findTotalCount(String category, Long postId);
	Long findTopTotalCount(String category, Long postId);
	int findCount(String category, String column, Long commentId);
	int updateCount(String category, String column, Long commentId);
	int updateContent(String category, Long commentId, String content);
	int deleteComment(String category, Long commentId);
}
