package com.board.web.security;

public interface EncryptiontSecurity {
	String makeSalt();
	String encryptPassword(String password, String salt);
}
