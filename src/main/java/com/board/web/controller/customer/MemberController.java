package com.board.web.controller.customer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.entity.Member;
import com.board.web.error.MemberError;
import com.board.web.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/regist")
	public String getRegPage() {
		return "regist";
	}
	
	@PostMapping
	public String postMember(String name, String nickname, String id,
			String password, String confirmationPassword, String email, Date birthday) {
		Member member = new Member(name, nickname, id, email, birthday);
		if (this.memberService.registMember(member, password, confirmationPassword) == MemberError.NO_ERROR) {
			return "redirect:/";
		} else {
			return "regist";			
		}
	}
	
	@ResponseBody
	@GetMapping("/nickname")
	public String getNickname(String nickname) {
		return "none";
	}
	
	@ResponseBody
	@GetMapping("/id")
	public String getId(String id) {
		return "none";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String postMember(String id, String password) {
		if (this.memberService.login(id, password)) {
			return "redirect:/";
		} else {
			return "login";			
		}
	}
	
}