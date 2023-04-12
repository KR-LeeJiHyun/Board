package com.board.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.web.entity.Member;
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
				String refreshToken = cookie.getValue();
				String id = memberService.findMemberIdByRefreshToken(refreshToken);				
				if (id == null) {					
					break;
				}	
				
				Member member = memberService.findMemberById(id);
				if (member == null) {
					break;
				}
				
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				return true;
			}
		}
		
		Cookie resCookie = new Cookie("GUEST", "1");
		response.addCookie(resCookie);
		return true;
    }
}
