package com.board.web.service;

import java.util.Date;
import com.board.web.entity.Member;
import com.board.web.error.MemberError;

public interface MemberService {
	MemberError registMember(Member member, String password, String confirmationPassword);
	String getMemberId(String id);
	String getMemberNickname();
	boolean validateId(String id);
	boolean validateDuplicateId(String id);
	boolean validatePassword(String password, String confirmationPassword);
	boolean validateBirthday(Date bitrhday);
	boolean validateNickname(String nickname);
	boolean validateDuplicateNickname(String nickname);
	boolean validateEmail(String email);
}
