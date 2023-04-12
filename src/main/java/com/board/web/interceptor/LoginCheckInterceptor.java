package com.board.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.web.entity.Member;

public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("Member");
        	if(member != null) {
        		return true;
        	}
        }
        
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    
	}
}
