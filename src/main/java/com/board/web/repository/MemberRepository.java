package com.board.web.repository;

import com.board.web.entity.Member;
import com.board.web.entity.PersistenceLogin;

public interface MemberRepository {
	int insertMember(Member member);
	String findMemberIdByRefreshToken(String refreshToken);
	Member findMemberById(String id);
	int insertRefreshToken(PersistenceLogin persistenceLogin);
	Member findMemberByField(String field, String value);
}
