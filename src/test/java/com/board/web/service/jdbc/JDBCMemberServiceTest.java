package com.board.web.service.jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JDBCMemberServiceTest {
	
	
	
	@Test
	void testGetMemberId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMemberNickname() {
		fail("Not yet implemented");
	}

	@Test
	void testRegistMember() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateBirthday() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateDuplicateId() {
		fail("Not yet implemented");
	}

	@Test
	void testValidatePassword() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateDuplicateNickname() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateId() {
		fail("Not yet implemented");
	}

	@Test
	void testValidateNickname() {
		JDBCMemberService jdbcMemberService = new JDBCMemberService(null, null);
		String nickName = "니다야러다이낭디야어라디아asefwe1";
		
		long start = System.currentTimeMillis();
		jdbcMemberService.validateNickname(nickName);
		long end = System.currentTimeMillis();
		System.out.println(end - start + "ms");
	}

}
