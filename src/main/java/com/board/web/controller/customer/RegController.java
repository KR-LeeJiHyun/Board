package com.board.web.controller.customer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.web.service.RegService;

@RequestMapping("/member")
public class RegController {
	
	private RegService regService;
	
	@Autowired	
	public RegController(RegService regService) {
		this.regService = regService;
	}

	@GetMapping
	public String getReg() {
		return "regist";
	}
	
	@PostMapping
	public String postReg(String name, String nickname, String id,
			String password, String confirmationPassword, String email,
			String address, Date birthday) {	
		if (this.regService.registMember(name, nickname, id, password, confirmationPassword, email, birthday)) {
			return "redirect:/";
		} else {
			return "regist";			
		}
	}
}