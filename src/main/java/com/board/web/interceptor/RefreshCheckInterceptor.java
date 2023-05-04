package com.board.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.board.web.entity.Member;
import com.board.web.repository.MemberRepository;
public class RefreshCheckInterceptor implements HandlerInterceptor {
	
	private MemberRepository memberRepository;
	
	public RefreshCheckInterceptor(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("REFRESH_TOKEN")) {
					//멤버 서비스
					String refreshToken = cookie.getValue();
					String id = memberRepository.findMemberIdByRefreshToken(refreshToken);				
					if (id == null) {					
						break;
					}	

					Member member = memberRepository.findMemberById(id);
					if (member == null) {
						break;
					}
					
					session = request.getSession();
					session.setAttribute("member", member);
				}
			}
		}
		
		if(session == null) {
			session = request.getSession();
			session.setAttribute("member", new Member());
		}		
		
		return false;
	}
}
