package com.board.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.web.service.MemberService;

@Component
public class RefreshCheckInterceptor implements HandlerInterceptor {
	
	private MemberService memberService;
	
	@Autowired	
	public RefreshCheckInterceptor(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("REFRESH_TOKEN")) {
				//멤버 서비스
			}
		}
		
		Cookie resCookie = new Cookie("GUEST", "1");
		response.addCookie(resCookie);
		return true;
    }
}
