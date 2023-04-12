package com.board.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals("JSESSIONID")) {
				HttpSession session = request.getSession(false);
				if(session == null) {
					//401 반환
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
				String memberId = (String)session.getAttribute("MeberId");
				if(memberId != null) {
					request.setAttribute("MemberId", memberId);
					return true;
				}
				else {
					//401반환
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				}
			}
			else if(cookie.getName().equals("GUEST")){
				return true;
			}
			else {
				//401 반환
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}
		}
		
		//401 반환
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		return false;
    }
}
