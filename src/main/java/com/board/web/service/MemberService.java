package com.board.web.service;

import java.util.Date;
import com.board.web.entity.Member;

public interface MemberService {
	boolean registMember(Member member);
	String getMemberId();
	String getMemberNickname();
	boolean validateDuplicateId(String id);
	boolean validatePassword(String id);
	boolean validateBirthday(String id);
}
