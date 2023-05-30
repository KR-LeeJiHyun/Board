package com.board.web.entity;

public class PersistenceLogin {
	private String refreshToken;
	private String id;
	
	public PersistenceLogin(String refreshToken, String id) {
		this.refreshToken = refreshToken;
		this.id = id;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public String getId() {
		return id;
	}
}
