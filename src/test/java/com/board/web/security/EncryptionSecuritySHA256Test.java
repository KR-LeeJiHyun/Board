package com.board.web.security;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EncryptionSecuritySHA256Test {

	@Test
	void testMakeSalt() throws UnsupportedEncodingException {
		EncryptionSecuritySHA256 security = new EncryptionSecuritySHA256();
		
		System.out.println((char)190);
		for (int idx = 0; idx < 10; ++idx) {
			String salt = security.makeSalt();
			System.out.println("salt : " + salt);
			System.out.println(salt.getBytes().length);
			System.out.println(salt.length());
			Assertions.assertEquals(32, salt.length());
		}
	}
}
