package com.board.web.repository;

public class PostSearch {
	
	private String field;
	private String query;
	private String order;
	private int page;
	
	public PostSearch() {
		this.field = "TITLE";
		this.query = "";
		this.order = "REGDATE";
		this.page = 1;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
