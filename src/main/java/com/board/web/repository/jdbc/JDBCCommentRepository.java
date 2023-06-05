package com.board.web.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.board.web.controller.comment.CommentForm;
import com.board.web.entity.Comment;
import com.board.web.entity.Member;
import com.board.web.repository.CommentRepository;

@Repository
public class JDBCCommentRepository implements CommentRepository {

	private final DataSource dataSource;

	public JDBCCommentRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int insertComment(Member member, CommentForm commentForm) {
		String sql = "INSERT INTO " + commentForm.getCategory() + "_COMMENT(MEMBER_ID, WRITER, CONTENT, POST_ID, PARENT_ID) VALUES(?,?,?,?,?)";

		int result = 0;
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DataSourceUtils.getConnection(dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, member.getId());
			preparedStatement.setString(2, member.getNickname());
			preparedStatement.setString(3, commentForm.getContent());
			preparedStatement.setLong(4, commentForm.getPostId());
			preparedStatement.setLong(5, commentForm.getParentId());

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		} finally {
			close(preparedStatement, con);
		}

		return result;
	}

	@Override
	public Comment findOne(String category, Long commentId) {
		String sql ="SELECT * FROM " + category + "_COMMENT WHERE COMMENT_ID = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Comment comment = null;

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, commentId);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				Long id = rs.getLong("COMMENT_ID");
				Long parentId = rs.getLong("PARENT_ID");
				Long postId = rs.getLong("POST_ID");
				String memberId = rs.getString("MEMBER_ID");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getDate("REGDATE");
				int like = rs.getInt("LIKE");
				int unlike = rs.getInt("LIKE");
				
				comment = new Comment(id, parentId, postId, memberId, writer, content, regdate, like, unlike);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			close(preparedStatement, con);
		}
		
		return comment;
	}

	@Override
	public List<Comment> findComments(String category, Long postId, int number) {
		String sql ="SELECT *\r\n"
				+ " FROM " + category + "_COMMENT\r\n"
				+ " WHERE POST_ID = ?\r\n"
				+ " START WITH PARENT_ID = 0 \r\n"
				+ " CONNECT BY PARENT_ID = PRIOR COMMENT_ID";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, postId);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("COMMENT_ID");
				Long parentId = rs.getLong("PARENT_ID");
				String memberId = rs.getString("MEMBER_ID");
				String writer = rs.getString("WRITER");
				String content = rs.getString("CONTENT");
				Date regdate = rs.getDate("REGDATE");
				int like = rs.getInt("LIKE");
				int unlike = rs.getInt("LIKE");
				
				Comment comment = new Comment(id, parentId, postId, memberId, writer, content, regdate, like, unlike);
				list.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			close(preparedStatement, con);
		}
		
		return list;
	}
	
	@Override
	public int findCount(String category, String column, Long commentId) {
		String sql ="SELECT \"" + column +"\" FROM " + category + "_COMMENT WHERE COMMENT_ID = ?";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count = 0;

		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, commentId);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				count = rs.getInt(column);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			
			close(preparedStatement, con);
		}
		
		return count;
	}

	@Override
	public int updateContent(String category, Long commentId, String content) {
		String sql = "UPDATE " + category + "_COMMENT SET CONTENT = ? WHERE COMMENT_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, content);
			preparedStatement.setLong(2, commentId);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(preparedStatement, con);
		}
		
		return result;
	}

	@Override
	public int updateCount(String category, String column, Long commentId) {
		String sql = "UPDATE " + category + "_COMMENT SET \"" + column + "\" = \"" + column + "\" + 1 " + " WHERE COMMENT_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, commentId);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(preparedStatement, con);
		}
		
		return result;
	}

	@Override
	public int deleteComment(String category, Long commentId) {
		String sql = "DELETE  " + category + "_COMMENT WHERE COMMENT_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, commentId);
			
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(preparedStatement, con);
		}
		
		return result;
	}
	
	

	private void close(PreparedStatement preparedStatement, Connection con) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (con != null) {
			DataSourceUtils.releaseConnection(con, this.dataSource);
		}
	}

	
}
