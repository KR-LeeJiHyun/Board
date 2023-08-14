package com.board.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.board.web.entity.Member;

public class LoginCheck {
	public static Member getMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		
		Member member = (Member) session.getAttribute("member");
		return member;
	}
	
	public static boolean isLoggedIn(Member member) {
		if (member == null || member.getId() == null) {
			return false;
		}
		
		return true;
	}
}
