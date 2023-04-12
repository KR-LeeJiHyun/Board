package com.board.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.board.web.interceptor.LoginCheckInterceptor;
import com.board.web.interceptor.RefreshCheckInterceptor;
import com.board.web.service.MemberService;

@Configuration
public class InterceptorConfig {
	
	private MemberService memberService;
	
	public InterceptorConfig (MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Bean
	public MappedInterceptor loginCheckInterceptor() {
		String[] includePatterns = {"/**"};
		String[] excludePatterns = {"/members/**","/css/**","/js/**","/fonts/**","/images/**"};
	    return new MappedInterceptor(includePatterns, excludePatterns, new LoginCheckInterceptor());
	}
	
	@Bean
	public MappedInterceptor refreshCheckInterceptor() {
		String[] includePatterns = {"/refresh"};
		String[] excludePatterns = {"/**"};
	    return new MappedInterceptor(includePatterns, excludePatterns, new RefreshCheckInterceptor(memberService));
	}
}
