package com.board.web.service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
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
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		String result = "";

		try {
			Connection con = dataSource.getConnection();
			PreparedStatement prepared_statement = con.prepareStatement(sql);
			prepared_statement.setString(1, id);

			//게시글 얻어오기
			ResultSet rs = prepared_statement.executeQuery();

			if(rs.next()) {
				result = rs.getString("id");
			}
			prepared_statement.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
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
	
	@Override
	public boolean validateDuplicateNickname(String nickname) {
		String sql = "SELECT NICKNAME FROM MEMBER WHERE NICKNAME =" + nickname;
		
		boolean result = false;		
		Connection con = null;
		Statement st = null;		
		try {
			con = this.dataSource.getConnection();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			result = !rs.next();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				
				if (con != null) {
					con.close();
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public boolean validateEmail(String email) {
		boolean result = true;
		
		String regex = "\\w+@\\w+.\\w+(\\.\\w+)?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		result = matcher.matches();
		
		final int EMAIL_MAX_LENGTH = 320;
		if (email.length() > EMAIL_MAX_LENGTH) {
			result = false;
		}
		
		return result;
	}
}