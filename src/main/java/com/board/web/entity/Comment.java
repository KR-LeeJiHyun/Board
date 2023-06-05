package com.board.web.entity;

import java.util.Date;

public class Comment {
	private Long id;
	private Long parentId;
	private Long postId;
	private String memberId;
	private String writer;
	private String content;
	private Date regdate;
	private int like;
	private int unlike;
	
	public Comment(Long id, Long parentId, Long postId, String memberId, String writer, String content, Date regdate,
			int like, int unlike) {
		this.id = id;
		this.parentId = parentId;
		this.postId = postId;
		this.memberId = memberId;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.like = like;
		this.unlike = unlike;
	}

	public Long getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public Long getPostId() {
		return postId;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public int getLike() {
		return like;
	}

	public int getUnlike() {
		return unlike;
	}
}
