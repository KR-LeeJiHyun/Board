package com.board.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class EncryptionSecuritySHA256 implements EncryptiontSecurity {
	@Override
	public String makeSalt() {
		final int SALT_SIZE = 32;
		StringBuilder builder = new StringBuilder(SALT_SIZE);
		
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		for (int idx = 0; idx < SALT_SIZE; ++idx) {
			char randomChar = (char)(random.nextInt(95) + 32);
			builder.append(randomChar);
		}
		
		return builder.toString();
	}

	@Override
	public String encryptPassword(String password, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update((password + salt).getBytes());

			StringBuilder builder = new StringBuilder();
			for (byte b : md.digest()) {
				builder.append(String.format("%02x", b));
			}

			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

}
