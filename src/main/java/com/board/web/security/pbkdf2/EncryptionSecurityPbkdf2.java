package com.board.web.security.pbkdf2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

import com.board.web.security.EncryptiontSecurity;

@Component
public class EncryptionSecurityPbkdf2 implements EncryptiontSecurity {
	private Pbkdf2PasswordEncoder passwordEncoder;
	
	@Autowired
	public EncryptionSecurityPbkdf2(Pbkdf2PasswordEncoder passwordEncoder) {		
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String encryptPassword(String password) {		
		return this.passwordEncoder.encode(password);
	}

	@Override
	public boolean matches(String password, String encryptedPassword) {
		return this.passwordEncoder.matches(password, encryptedPassword);
	}
}