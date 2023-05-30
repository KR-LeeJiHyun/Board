package com.board.web.controller.customer;

import com.board.web.entity.Member;

public class LoginResult {
	private Member member;
	private String refreshToken;
	
	public LoginResult(Member member, String refreshToken) {
		this.member = member;
		this.refreshToken = refreshToken;
	}

	public Member getMember() {
		return member;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
}
