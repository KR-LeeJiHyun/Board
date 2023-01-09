package com.board.web.security;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.listeners.LegacyReportingUtils;

class EncryptionSecuritySHA256Test {

	@Test
	void testMakeSalt() throws UnsupportedEncodingException {
		EncryptionSecuritySHA256 security = new EncryptionSecuritySHA256();
		for (int idx = 0; idx < 10; ++idx) {
			String s = security.makeSalt();
			System.out.println(s);
			System.out.println(s.getBytes().length);
			System.out.println(s.length());
			Assertions.assertEquals(32, s.length());
		}
	}

}
