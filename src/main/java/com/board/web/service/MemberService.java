package com.board.web.service;

import java.util.Date;
import com.board.web.entity.Member;

public interface MemberService {
	boolean registMember(Member member, String confirmationPassword);
	String getMemberId(String id);
	String getMemberNickname();
	boolean validateDuplicateId(String id);
	boolean validatePassword(String password, String confirmationPassword);
	boolean validateBirthday(Date bitrhday);
}
