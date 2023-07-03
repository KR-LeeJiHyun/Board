package com.board.web.service.comment;

import java.util.ArrayList;
import java.util.List;

import com.board.web.repository.jdbc.comment.CommentDTO;

public class CommentDTONode {
	CommentDTO commentDTO;
	List<CommentDTONode> children;
	
	public CommentDTONode(CommentDTO commentDTO) {
		this.commentDTO = commentDTO;
		this.children = new ArrayList<>();
	}

	public CommentDTO getCommentDTO() {
		return commentDTO;
	}

	public List<CommentDTONode> getChildren() {
		return children;
	}
	
	public void addChild(CommentDTONode child) {
		children.add(child);
	}
}
