package com.board.web.service;

public interface MemberServiceSeo {
	boolean validateDuplicateNickname(String nickname);
	boolean validateEmail(String email);
}
