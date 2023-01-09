package com.board.web.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class EncryptionSecuritySHA256 implements EncryptiontSecurity {
	@Override
	public String makeSalt() {
		final int SALT_SIZE = 32;
		
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[SALT_SIZE];
		random.nextBytes(bytes);
		
		System.out.println(bytes);
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			builder.append(String.format("%02o", b));
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
