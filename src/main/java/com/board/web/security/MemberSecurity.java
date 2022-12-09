package com.board.web.security;

import java.util.Date;

public class MemberSecurity {	
	boolean checkDuplicateNickname(String nickname) {
		return true;
	}
	
	boolean checkDuplicateId(String id) {
		return true;
	}
	
	boolean verifyPassword(String password, String confirmationPassword) {
		return true;
	}
	
	boolean verifyEmail(String email) {
		return true;
	}
	
	boolean verifyBirthday(Date birthday) {
		return true;
	}	
}
