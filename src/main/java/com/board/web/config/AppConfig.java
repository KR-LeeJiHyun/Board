package com.board.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.board.web.interceptor.LoginCheckInterceptor;

@Configuration
public class AppConfig {
	@Bean
	public Pbkdf2PasswordEncoder pbkdf2PasswordEncoder() {
		return new Pbkdf2PasswordEncoder();
	} 
	
	@Bean
	public MappedInterceptor loginCheckInterceptor() {
		String[] includePatterns = {"/**"};
		String[] excludePatterns = {"/members/**","/css/**","/js/**","/fonts/**","/images/**"};
	    return new MappedInterceptor(includePatterns, excludePatterns, new LoginCheckInterceptor());
	}
}
