package com.board.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.board.web.interceptor.LoginCheckInterceptor;
import com.board.web.interceptor.RefreshCheckInterceptor;
import com.board.web.repository.MemberRepository;

@Configuration
public class InterceptorConfig {
	
	private MemberRepository memberRepository;
	
	public InterceptorConfig (MemberRepository memberRepository) {
		this.memberRepository= memberRepository;
	}
	
	@Bean
	public MappedInterceptor loginCheckInterceptor() {
		String[] includePatterns = {"/**"};
		String[] excludePatterns = {"/members/**","/css/**","/js/**","/fonts/**","/images/**", "/refresh/**", "/empty/**"};
	    return new MappedInterceptor(includePatterns, excludePatterns, new LoginCheckInterceptor());
	}
	
	@Bean
	public MappedInterceptor refreshCheckInterceptor() {
		String[] includePatterns = {"/refresh"};
	    return new MappedInterceptor(includePatterns, new RefreshCheckInterceptor(memberRepository));
	}
	
	@Bean
    public MappedInterceptor webContentInterceptor() {
        String[] includePatterns = {"/board/edit/**", "/board/new/**"};
        WebContentInterceptor wci = new WebContentInterceptor();
        CacheControl cacheControl = CacheControl.noStore().mustRevalidate();
        wci.addCacheMapping(cacheControl, "/**");

        return new MappedInterceptor(includePatterns, wci);
    }
}
