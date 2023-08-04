package com.board.web.repository;

import java.util.Date;

public class PostForm {
	
	private String memberId;
	private String writer;
	private String title;
	private String content;
	private String category;
	
	public PostForm(String memberId, String writer, String title, String content, String category) {
		this.memberId = memberId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.category = category;
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

	public String getCategory() {
		return category;
	} 
}
