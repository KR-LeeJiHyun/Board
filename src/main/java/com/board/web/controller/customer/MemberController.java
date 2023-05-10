package com.board.web.controller.customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ResponseEntity postMember(MemberForm memberForm) {
		MemberError result = this.memberService.registMember(memberForm);
		if (result == MemberError.NO_ERROR) {
			return new ResponseEntity<String>("회원가입을 축하드립니다!", HttpStatus.CREATED);
		} else if(result == MemberError.INVALID_ID) {
			return new ResponseEntity<String>("잘못된 ID입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_NICKNAME){
			return new ResponseEntity<String>("잘못된 닉네임입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_PASSWORD){
			return new ResponseEntity<String>("잘못된 비밀번호입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_EMAIL){
			return new ResponseEntity<String>("잘못된 이메일입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_BIRTHDAY){
			return new ResponseEntity<String>("잘못된 생년월일입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.DUPLICATE_ID){
			return new ResponseEntity<String>("중복된 아이디입니다.",HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.DUPLICATE_NICKNAME){
			return new ResponseEntity<String>("중복된 닉네임입니다.",HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>("문제가 발생했습니다. 다시시도해주세요!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/nickname")
	public ResponseEntity getNickname(String nickname) {
		if(memberService.existNickname(nickname)) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity(HttpStatus.OK);
		}
	}
	
	@GetMapping("/id")
	public ResponseEntity getId(String id) {
		if(memberService.existId(id)) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity(HttpStatus.OK);
		}
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
    public ModelAndView postMember(HttpServletRequest request, HttpServletResponse response, String id, String password, String loginKeep) {
        ModelAndView mv = new ModelAndView();
        LoginResult loginResult = this.memberService.login(id, password, loginKeep);
        Member member = loginResult.getMember();
        if (member != null) {
        	HttpSession session = request.getSession();
        	session.setAttribute("member", member);
        	String refreshToken = loginResult.getRefreshToken();
        	
        	if(refreshToken != null) {
        		Cookie refreshTokenCookie = new Cookie("REFRESH_TOKEN", refreshToken);
        		refreshTokenCookie.setPath("/community/refresh");
        		refreshTokenCookie.setMaxAge(60 * 60 * 24 * 30);
            	response.addCookie(refreshTokenCookie);
        	}
            
            System.out.println("login success");
            mv.setViewName("redirect:/");
        }
        else {
        	System.out.println("login fail");
            mv.setViewName("login");
            mv.setStatus(HttpStatus.UNAUTHORIZED);
            mv.addObject("error", true);
        }
        	
        return mv;
    }	
}