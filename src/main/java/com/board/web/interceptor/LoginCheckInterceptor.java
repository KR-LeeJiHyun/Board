package com.board.web.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.board.web.entity.Member;

public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("member");
        	if(member != null) {        		
        		return true;
        	}
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/empty"); // /빼면 상대주소인지 확인하기
      	dispatcher.forward(request, response);
        return false;
	}
}
