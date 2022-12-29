package com.board.web.controller.customer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.web.entity.Member;
import com.board.web.service.MemberService;

@RequestMapping("/member")
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
		if (this.memberService.registMember(member)) {
			return "redirect:/";
		} else {
			return "regist";			
		}
	}
}