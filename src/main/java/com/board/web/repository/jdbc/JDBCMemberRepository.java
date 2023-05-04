package com.board.web.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.board.web.entity.Member;
import com.board.web.entity.PersistenceLogin;
import com.board.web.repository.MemberRepository;

@Repository
public class JDBCMemberRepository implements MemberRepository{
	private DataSource dataSource;

	@Autowired
	public JDBCMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int insertMember(Member member) {
		String sql = "INSERT INTO MEMBER(NAME, ID, PASSWORD, NICKNAME, EMAIL, BIRTHDAY, ROLE) VALUES(?,?,?,?,?,?,?)";
		
		int result = 0;		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {	
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getId());
			preparedStatement.setString(3, member.getEncryptedPassword());
			preparedStatement.setString(4, member.getNickname());
			preparedStatement.setString(5, member.getEmail());
			preparedStatement.setDate(6, new java.sql.Date(member.getBirthday().getTime()));
			preparedStatement.setInt(7, 0);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			result = 0;
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					DataSourceUtils.releaseConnection(con, this.dataSource);
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public String findMemberIdByRefreshToken(String refreshToken) {
		String sql = "SELECT MEMBER_ID FROM PERSISTENCE_LOGINS WHERE TOKEN = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String id = null;

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, refreshToken);

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				id = rs.getString("MEMBER_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					DataSourceUtils.releaseConnection(con, this.dataSource);
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}

	@Override
	public Member findMemberByField(String field, String value) {
		String sql = "SELECT * FROM MEMBER WHERE " + field +  " = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		Member member = null;

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, value);

			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				String nickname = rs.getString("NICKNAME");				
				String email = rs.getString("EMAIL");
				Date birthday = rs.getDate("BIRTHDAY");
				String encryptedPassword = rs.getString("PASSWORD");
				
				member = new Member(name, nickname, id, email, birthday, encryptedPassword);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					DataSourceUtils.releaseConnection(con, this.dataSource);
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}
	
	
	@Override
	public Member findMemberById(String id) {
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		Member member = null;

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				String name = rs.getString("NAME");
				String nickname = rs.getString("NICKNAME");				
				String email = rs.getString("EMAIL");
				Date birthday = rs.getDate("BIRTHDAY");
				String encryptedPassword = rs.getString("PASSWORD");
				
				member = new Member(name, nickname, id, email, birthday, encryptedPassword);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					DataSourceUtils.releaseConnection(con, this.dataSource);
				}							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return member;
	}

	@Override
	public int insertRefreshToken(PersistenceLogin persistenceLogin) {
		int result = 0;
		String sql = "INSERT INTO PERSISTENCE_LOGINS(MEMBER_ID, TOKEN) VALUES(?,?)";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, persistenceLogin.getId());
			preparedStatement.setString(2, persistenceLogin.getRefreshToken());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
				if (con != null) {
					DataSourceUtils.releaseConnection(con, this.dataSource);
				}							
			} catch (SQLException e) {
				result = 0;
				e.printStackTrace();
			}
		}
		
		return result;
	}
}