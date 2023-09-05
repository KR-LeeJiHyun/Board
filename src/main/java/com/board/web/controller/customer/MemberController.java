package com.board.web.controller.customer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.web.common.LoginCheck;
import com.board.web.entity.Member;
import com.board.web.error.MemberError;
import com.board.web.error.MemberErrorMessageId;
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
			return new ResponseEntity<String>(MemberErrorMessageId.INVALID_ID, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_NICKNAME){
			return new ResponseEntity<String>(MemberErrorMessageId.INVALID_NICKNAME, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_PASSWORD){
			return new ResponseEntity<String>(MemberErrorMessageId.INVALID_PASSWORD, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_EMAIL){
			return new ResponseEntity<String>(MemberErrorMessageId.INVALID_EMAIL, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.INVALID_BIRTHDAY){
			return new ResponseEntity<String>(MemberErrorMessageId.INVALID_BIRTHDAY, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.DUPLICATE_ID){
			return new ResponseEntity<String>(MemberErrorMessageId.DUPLICATE_ID, HttpStatus.BAD_REQUEST);
		} else if(result == MemberError.DUPLICATE_NICKNAME){
			return new ResponseEntity<String>(MemberErrorMessageId.DUPLICATE_NICKNAME, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(MemberErrorMessageId.SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/nickname")
	public ResponseEntity getNickname(String nickname) {
		if(memberService.existNickname(nickname)) {
			return new ResponseEntity(MemberErrorMessageId.DUPLICATE_NICKNAME, HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity(HttpStatus.OK);
		}
	}
	
	@GetMapping("/id")
	public ResponseEntity getId(String id) {
		if(memberService.existId(id)) {
			return new ResponseEntity(MemberErrorMessageId.DUPLICATE_ID, HttpStatus.CONFLICT);
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
    public ModelAndView postMember(HttpServletRequest request, HttpServletResponse response, String id, String password, String loginKeep, String redirectURL) {
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
        		refreshTokenCookie.setHttpOnly(true);
        		refreshTokenCookie.setMaxAge(60 * 60 * 24 * 30);
            	response.addCookie(refreshTokenCookie);
            	Cookie logoutTokenCookie = new Cookie("LOGOUT_TOKEN", refreshToken);
            	logoutTokenCookie.setPath("/community/members/logout");
            	logoutTokenCookie.setHttpOnly(true);
            	logoutTokenCookie.setMaxAge(60 * 60 * 24 * 30);
            	response.addCookie(logoutTokenCookie);
        	}
            
        	if (redirectURL != null) {
        		mv.setViewName("redirect:" + redirectURL);
        	} else {
        		mv.setViewName("redirect:/");
        	}
        }
        else {
            mv.setViewName("login");
            mv.setStatus(HttpStatus.UNAUTHORIZED);
            mv.addObject("error", true);
        }
        	
        return mv;
    }	
	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Member member = new Member();
			session.setAttribute("member", member);			
		}

		// refresh 토큰 삭제
		for(Cookie c : request.getCookies()) {
			if(c.getName().equals("LOGOUT_TOKEN")) {
				String refreshToken = c.getValue();
				int result = memberService.logout(refreshToken);
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/mypage")
	public String getMyPage(HttpServletRequest request, Model model) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {	
			Profile profile = new Profile(member.getName(), member.getNickname(), member.getId(), member.getEmail());
			model.addAttribute("profile", profile);
			return "myPage";
		}
		else {
			return "redirect:/members/login";
		}
	}
	
	@GetMapping("/mypage/nickname")
	public String getMyPageNickname(HttpServletRequest request) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {	
			return "nickname";
		}
		else {
			return "redirect:/members/login";
		}
	}

	@PostMapping("/mypage/nickname")
	public ResponseEntity updateNickname(HttpServletRequest request, String nickname, String password) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			NicknameForm form = new NicknameForm(member.getId(), nickname, password);
			MemberError result = memberService.updateNickname(form);
			if (result == MemberError.NO_ERROR) {
				Member updateMember = new Member(member.getName(), nickname, member.getId(), member.getEmail(), member.getBirthday(), member.getEncryptedPassword());
				HttpSession session = request.getSession(false);
				session.setAttribute("member", updateMember);
				
				return new ResponseEntity("닉네임이 변경되었습니다.", HttpStatus.OK);
 			} else if (result == MemberError.INVALID_EMAIL) {
 				return new ResponseEntity("사용할 수 없는 닉네임입니다.", HttpStatus.BAD_REQUEST);
 			} else if (result == MemberError.DUPLICATE_NICKNAME) {
 				return new ResponseEntity("중복된 닉네임입니다.", HttpStatus.BAD_REQUEST);
 			} else if (result == MemberError.WRONG_PASSWORD) {
 				return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
 			} else {
 				return new ResponseEntity("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
 			}			
		} else {
			return new ResponseEntity("로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/mypage/password")
	public String getMyPagePassword(HttpServletRequest request) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {	
			return "password";
		}
		else {
			return "redirect:/members/login";
		}
	}
	
	@PostMapping("/mypage/password")
	public ResponseEntity postMyPagePassword(HttpServletRequest request, String currentPassword, String password, String confirmationPassword) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			PasswordForm passwordForm = new PasswordForm(member.getId(), currentPassword, password, confirmationPassword);
			if(memberService.updatePassword(passwordForm)) {
				HttpSession session = request.getSession(false);
				session.setAttribute("member", new Member());
				return new ResponseEntity("비밀번호 변경되었습니다.",HttpStatus.OK);
			}
		}

		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/mypage/email")
	public String getMyPageEmail(HttpServletRequest request) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {	
			return "email";
		}
		else {
			return "redirect:/members/login";
		}
	}

	@PostMapping("/mypage/email")
	public ResponseEntity updateEmail(HttpServletRequest request, String email, String password) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			EmailForm form = new EmailForm(member.getId(), email, password);
			MemberError result = memberService.updateEmail(form);
			if (result == MemberError.NO_ERROR) {
				Member updateMember = new Member(member.getName(), member.getNickname(), member.getId(), email, member.getBirthday(), member.getEncryptedPassword());
				HttpSession session = request.getSession(false);
				session.setAttribute("member", updateMember);
				
				return new ResponseEntity("이메일이 변경되었습니다.", HttpStatus.OK);
 			} else if (result == MemberError.INVALID_EMAIL) {
 				return new ResponseEntity("사용할 수 없는 이메일입니다.", HttpStatus.BAD_REQUEST);
 			} else if (result == MemberError.WRONG_PASSWORD) {
 				return new ResponseEntity("비밀번호가 일치하지 않습니다", HttpStatus.UNAUTHORIZED);
 			} else {
 				return new ResponseEntity("서버 오류입니다", HttpStatus.INTERNAL_SERVER_ERROR);
 			}			
		} else {
			return new ResponseEntity("로그인을 해주세요.", HttpStatus.UNAUTHORIZED);
		}
	}	
}