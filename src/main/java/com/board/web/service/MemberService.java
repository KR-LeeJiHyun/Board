package com.board.web.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.board.web.entity.Member;
import com.board.web.entity.PersistenceLogin;
import com.board.web.error.MemberError;
import com.board.web.repository.MemberRepository;
import com.board.web.security.EncryptiontSecurity;
import com.board.web.controller.customer.LoginResult;
import com.board.web.controller.customer.MemberForm;

@Service
public class MemberService {
	private EncryptiontSecurity encryptiontSecurity;
	private DataSource dataSource;
	private MemberRepository memberRepository;

	@Autowired
	public MemberService(EncryptiontSecurity encryptiontSecurity, DataSource dataSource, MemberRepository memberRepository) {
		this.encryptiontSecurity = encryptiontSecurity;
		this.dataSource = dataSource;
		this.memberRepository = memberRepository;
	}
	
	public MemberError registMember(MemberForm meberForm) {
		if (!validateBirthday(meberForm.getBirthday())) {
			return MemberError.INVALID_BIRTHDAY;
		}
		
		if (!validateId(meberForm.getId())) {
			return MemberError.INVALID_ID;
		}
		
		if (!validatePassword(meberForm.getPassword(), meberForm.getConfirmationPassword())) {
			return MemberError.INVALID_PASSWORD;
		}
		
		if (!validateNickname(meberForm.getNickname())) {
			return MemberError.INVALID_NICKNAME;
		}
		
		if (!validateEmail(meberForm.getEmail())) {
			return MemberError.INVALID_EMAIL;
		}
		
		if(existId(meberForm.getId())) {
			return MemberError.DUPLICATE_ID;
		}
		
		if(existNickname(meberForm.getNickname())) {
			return MemberError.DUPLICATE_NICKNAME;
		}
		
		String encryptedPassword = this.encryptiontSecurity.encryptPassword(meberForm.getPassword());
		
		Member member = new Member(meberForm.getName(), meberForm.getNickname(), meberForm.getId(), meberForm.getEmail(), meberForm.getBirthday(), encryptedPassword);
		int result = memberRepository.insertMember(member);
		if(result == 1) {
			return MemberError.NO_ERROR;
		}
		else {
			return MemberError.DB_FAIL;
		}
	}
	
	public boolean validateBirthday(Date birthday) {
		Date now = java.sql.Date.valueOf(LocalDate.now());
		return birthday.compareTo(now) < 1;
	}
	
	public boolean validatePassword(String password, String confirmationPassword) {		
		// 숫자
		String numberPattern = "(.*)[0-9](.*)$";
		// 영문자
		String alphabetPattern = "(.*)[a-zA-Z](.*)$";
		// 특수문자
		String specialPattern = "(.*)[`~!@#$%\\\\^&*()-_=+\\\\|[{]};:'\\\",<.>/?](.*)$";
		// 포함 문자
		String allPattern = "^[a-zA-Z0-9`~!@#$%\\^&*()-_=+\\|[{]};:'\",<.>/?]{8,20}$";

		// 확인용 비밀번호와 일치하는지 확인
		if (password.compareTo(confirmationPassword) != 0) {
			return false;
		}
		// 숫자, 문자, 특수문자가 알맞게 들어가있는지 확인
		else if (!Pattern.matches(numberPattern, password) || !Pattern.matches(alphabetPattern, password)
				|| !Pattern.matches(specialPattern, password) || !Pattern.matches(allPattern, password)) {
			return false;
		}

		return true;
	}

	public boolean validateEmail(String email) {
		System.out.println(email);
		String pattern = "\\w+@\\w+.\\w+(\\.\\w+)?{3,320}";
		return Pattern.matches(pattern, email);
	}
	
	public boolean validateId(String id) {
		String pattern = "^[a-zA-Z0-9_-]{5,20}$";
		return Pattern.matches(pattern, id);
	}
	
	public boolean validateNickname(String nickname) {
		String pattern = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣_-]{5,20}$";
		return Pattern.matches(pattern, nickname);
	}
	
	public boolean existId(String id) {
		Member member = memberRepository.findMemberByField("id", id);
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean existNickname(String nickname) {
		Member member = memberRepository.findMemberByField("nickname", nickname);
		if (member != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public LoginResult login(String id, String password, String loginKeep) {
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Member member = null;
        String resfreshToken = null;
		try{
			member = memberRepository.findMemberById(id);
			if(encryptiontSecurity.matches(password, member.getEncryptedPassword())) {
				if (loginKeep != null) {
        			resfreshToken = UUID.randomUUID().toString();
        			PersistenceLogin persistenceLogin = new PersistenceLogin(resfreshToken, id);
            		this.memberRepository.insertRefreshToken(persistenceLogin);
        		}
			}
			else {
				member = null;
			}
			transactionManager.commit(status);
		} catch(RuntimeException e) {
			member = null;
			transactionManager.rollback(status);
		}
		
		return new LoginResult(member, resfreshToken);
	}
}
