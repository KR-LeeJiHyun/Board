package com.board.web.service.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.board.web.entity.Member;
import com.board.web.service.MemberService;
import com.board.web.service.MemberServiceSeo;

public class JDBCMemberServiceSeo implements MemberServiceSeo {
	private DataSource dataSource;
	
	@Autowired
	public JDBCMemberServiceSeo(DataSource dataSource) {
		this.dataSource = dataSource;
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
