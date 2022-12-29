package com.board.web.service.jdbc;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.web.entity.Member;
import com.board.web.service.MemberService;

@Service
public class JDBCMemberService implements MemberService{
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public String getMemberId(String id) {
		return "";
	}
	
	@Override
	public String getMemberNickname() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean registMember(Member member, String confirmationPassword) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean validateBirthday(Date birthday) {
		Date now = java.sql.Date.valueOf(LocalDate.now());
		if(birthday.compareTo(now) < 1) return true;
		return false;
	}
	
	@Override
	public boolean validateDuplicateId(String id) {
		if(id.compareTo(getMemberId(id)) == 0) return false;
		return true;
	}
	
	@Override
	public boolean validatePassword(String password, String confirmationPassword) {
		//숫자
		String numberPattern = "*[0-9]*$";
		//영문자
		String alphabetPattern = "*[a-zA-Z]*$";
		//특수문자
		String specialPattern = "*[~!@\\#$%<>^&*]*$";
		int len = password.length();
		
		//길이 확인
		if(len < 8 && len > 20) return false;
		//확인용 비밀번호와 일치하는지 확인
		else if(password.compareTo(confirmationPassword) != 0) return false;
		//숫자, 문자, 특수문자가 알맞게 들어가있는지 확인
		else if(Pattern.matches(password, numberPattern) && Pattern.matches(password, alphabetPattern) && Pattern.matches(password, specialPattern)) return false;
		return true;
	}
}
