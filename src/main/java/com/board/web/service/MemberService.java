package com.board.web.service;

import java.util.Date;
import com.board.web.entity.Member;
import com.board.web.error.MemberError;

public interface MemberService {
	MemberError registMember(Member member, String password, String confirmationPassword);
	boolean validateId(String id);
	boolean existId(String id);
	boolean validatePassword(String password, String confirmationPassword);
	boolean validateBirthday(Date bitrhday);
	boolean validateNickname(String nickname);
	boolean existNickname(String nickname);
	boolean validateEmail(String email);
	boolean login(String id, String password);
	String findMemberIdByRefreshToken(String refreshToken);
	Member findMemberById(String id);
}
