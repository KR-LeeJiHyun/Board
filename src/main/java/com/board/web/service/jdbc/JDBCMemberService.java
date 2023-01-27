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
import com.board.web.error.MemberError;
import com.board.web.security.EncryptiontSecurity;
import com.board.web.service.MemberService;

@Service
public class JDBCMemberService implements MemberService{
	private EncryptiontSecurity encryptiontSecurity;
	private DataSource dataSource;

	@Autowired
	public JDBCMemberService(EncryptiontSecurity encryptiontSecurity, DataSource dataSource) {
		this.encryptiontSecurity = encryptiontSecurity;
		this.dataSource = dataSource;
	}
	
	@Override
	public MemberError registMember(Member member, String password, String confirmationPassword) {
		
		if (!validateBirthday(member.getBirthday())) {
			return MemberError.INVALID_BIRTHDAY;
		}
		
		if (!validateId(member.getId())) {
			return MemberError.INVALID_ID;
		}
		
		if (!validateDuplicateId(member.getId())) {
			return MemberError.DUPLICATE_ID;
		}
		
		if (!validatePassword(password, confirmationPassword)) {
			return MemberError.INVALID_PASSWORD;
		}

		if (!validateNickname(member.getNickname())) {
			return MemberError.INVALID_NICKNAME;
		}
		
		if (!validateDuplicateId(member.getNickname())) {
			return MemberError.DUPLICATE_NICKNAME;
		}
		
		if (!validateEmail(member.getEmail())) {
			return MemberError.INVALID_EMAIL;
		}
		
		String encryptedPassword = this.encryptiontSecurity.encryptPassword(password);
		
		String sql = "INSERT INTO MEMBER(NAME, ID, PASSWORD, NICKNAME, EMAIL, BIRTHDAY, ROLE) VALUES(?,?,?,?,?,?,?)";
		
		boolean result = false;		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {
			con = this.dataSource.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, member.getId());
			preparedStatement.setString(2, member.getId());
			preparedStatement.setString(3, encryptedPassword);
			preparedStatement.setString(4, member.getId());
			preparedStatement.setString(5, member.getId());
			preparedStatement.setString(6, member.getId());
			preparedStatement.setString(7, member.getId());
			
			result = preparedStatement.execute();			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					con.close();
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (result) {
			return MemberError.NO_ERROR;
		} else {
			return MemberError.DB_FAIL;
		}
	}
	
	@Override
	public boolean validateBirthday(Date birthday) {
		Date now = java.sql.Date.valueOf(LocalDate.now());
		return birthday.compareTo(now) < 1;
	}
	
	@Override
	public boolean validateDuplicateId(String id) {
		String sql = "SELECT ID FROM MEMBER WHERE ID = ?";
		String result = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = dataSource.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					con.close();
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result != null;
	}
	
	@Override
	public boolean validatePassword(String password, String confirmationPassword) {
	      //숫자
	      String numberPattern = "*[0-9]*$";
	      //영문자
	      String alphabetPattern = "*[a-zA-Z]*$";
	      //특수문자
	      String specialPattern = "*[`~!@#$%\\\\^&*()-_=+\\\\|[{]};:'\\\",<.>/?]*$";
	      //포함 문자
	      String allPattern = "^[a-zA-Z0-9`~!@#$%\\^&*()-_=+\\|[{]};:'\",<.>/?]{8,20}$";
	      
	      //확인용 비밀번호와 일치하는지 확인
	      if(password.compareTo(confirmationPassword) != 0) {
	    	  return false;
	      }
	      //숫자, 문자, 특수문자가 알맞게 들어가있는지 확인
	      else if(Pattern.matches(password, numberPattern) && Pattern.matches(password, alphabetPattern) && Pattern.matches(password, specialPattern) && Pattern.matches(password, allPattern)) {
	    	  return false;
	      }
	      
	      return true;
	}
	
	@Override
	public boolean validateDuplicateNickname(String nickname) {
		String sql = "SELECT NICKNAME FROM MEMBER WHERE ID = NICKNAME";
		String result = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = dataSource.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, nickname);

			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				result = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					con.close();
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result != null;
	}

	@Override
	public boolean validateEmail(String email) {
		String regex = "\\w+@\\w+.\\w+(\\.\\w+)?{3,320}";
		return Pattern.matches(email, regex);
	}
	
	@Override
	public boolean validateId(String id) {
		String allPattern = "^[a-zA-Z0-9_-]{5,20}$";
		return Pattern.matches(id, allPattern);
	}
	
	@Override
	public boolean validateNickname(String nickname) {
		String allPattern = "^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣_-]{5,20}$";
		return Pattern.matches(nickname, allPattern);
	}
	
	@Override
	public boolean login(String id, String password) {
		String sql = "SELECT PASSWORD FROM MEMBER WHERE ID = ?";
		String encryptedPassword = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;


		try {
			con = dataSource.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				encryptedPassword = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					con.close();
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return encryptiontSecurity.matches(password, encryptedPassword);
	}
}