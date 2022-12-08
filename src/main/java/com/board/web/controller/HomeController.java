package com.board.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping(value = {"index",""})
	public String index(Model model, Integer page, String field, String query) {
		return "index";
	}
}

