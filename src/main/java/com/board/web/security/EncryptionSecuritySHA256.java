package com.board.web.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class EncryptionSecuritySHA256 implements EncryptiontSecurity {
	@Override
	public String makeSalt() {
		final int SALT_SIZE = 8;
		
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[SALT_SIZE];
		random.nextBytes(bytes);
		
		String s;
		try {			
			s = new String(bytes, 0, bytes.length, "UTF-8");
			System.out.println("utf-8 : " + s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(bytes);
		StringBuilder builder = new StringBuilder();
		
		
		for (byte b : bytes) {
			builder.append(String.format("%02x", b));
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
