package com.board.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        System.out.println("preHandle");  
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println();
        return true;
    }	
	
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mav)
	
			throws Exception {
		System.out.println("postHandle");  
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());
        System.out.println();
	}
}
