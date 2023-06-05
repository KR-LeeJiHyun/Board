package com.board.web.entity;

import java.util.Date;

public class Post {
	
	private Long postId;
	private String memberId;
	private String writer;
	private String title;
	private String content;
	private Date regdate;
	private Integer like;
	private Integer unlike;
	private Integer hit;
	private String category;
	
	public Post(Long postId, String memberId, String writer, String title, String content, Date regdate, Integer like,
			Integer unlike, Integer hit, String category) {
		this.postId = postId;
		this.memberId = memberId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.like = like;
		this.unlike = unlike;
		this.hit = hit;
		this.category = category;
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
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public Integer getLike() {
		return like;
	}
	public Integer getUnlike() {
		return unlike;
	}
	public Integer getHit() {
		return hit;
	}
	public String getCategory() {
		return category;
	}
	
	
}
