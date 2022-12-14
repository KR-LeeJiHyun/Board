package com.board.web.service;

import java.util.Date;
import com.board.web.entity.Member;

public interface RegService {
	boolean registMember(Member member);
	Member getMember();
	String getMemberId();
	String getMemberNickname();
}
