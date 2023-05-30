package com.board.web.security;

public interface EncryptiontSecurity {
	String encryptPassword(String password);
	boolean matches(String password, String encryptedPassword);
}
