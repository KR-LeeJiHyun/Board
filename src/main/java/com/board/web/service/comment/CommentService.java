package com.board.web.service.comment;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.web.controller.comment.CommentForm;
import com.board.web.entity.Comment;
import com.board.web.entity.Member;
import com.board.web.repository.CommentRepository;
import com.board.web.repository.jdbc.comment.CommentDTO;

@Transactional
@Service
public class CommentService {
	
	private final DataSource dataSource;
	private final CommentRepository commentRepository;
	
	public CommentService(DataSource dataSource, CommentRepository commentRepository) {
		this.dataSource = dataSource;
		this.commentRepository = commentRepository;
	}

	public Comment createComment(Member member, CommentForm commentForm) {
		Comment comment = null;
		
		Long id = commentRepository.insertComment(member, commentForm);		
		if (id != 0) {
			comment = commentRepository.findOne(commentForm.getCategory(), id);
		}
		
		return comment;
	}
	
	public List<CommentDTO> findComments(String category, Long postId, int page) {
		return commentRepository.findComments(category, postId, page);
	}
	
	public List<CommentDTONode> findReplyComments(String category, Long postId, Long parentId) {
		List<CommentDTONode> nodes = new ArrayList<>();
		List<CommentDTO> list = commentRepository.findReplyComments(category, postId, parentId);
		for (int idx = 0, SIZE = list.size(); idx < SIZE; ++idx) {
			CommentDTO comment = list.get(idx);
			int level = comment.getLevel();
			if (level == 1) {
				CommentDTONode node = new CommentDTONode(comment);
				nodes.add(node);
			} else {
				addCommentDTO(nodes.get(nodes.size() - 1), comment);
			}
		}
		
		return nodes;
	}
	
	private void addCommentDTO(CommentDTONode parent, CommentDTO comment) {
		int parentLevel = parent.getCommentDTO().getLevel();
		int level = comment.getLevel();
		
		List<CommentDTONode> children = parent.getChildren();
		if (level == parentLevel + 1) {
			CommentDTONode node = new CommentDTONode(comment);			
			children.add(node);			
		} else {
			int lastIndex = children.size() - 1;
			addCommentDTO(children.get(lastIndex), comment);
		}
	}
	
	public Long findCommentTotal(String category, Long postId) {
		return commentRepository.findTotalCount(category, postId);
	}
	
	public Long findTopCommentTotal(String category, Long postId) {
		return commentRepository.findTopTotalCount(category, postId);
	}
	
	public CommentDTO updateContent(Member member, String category, Long commentId, String content) {
		CommentDTO result = null;
		if (isMyComment(member, category, commentId)) {
			if(commentRepository.updateContent(category, commentId, content) == 1) {
				Comment comment = commentRepository.findOne(category, commentId);
				result = new CommentDTO(comment.getId(), comment.getMemberId(), comment.getWriter(), comment.getContent(), comment.getRegdate(), comment.getLike(), comment.getUnlike(), 0, comment.getBlind());
			}
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
