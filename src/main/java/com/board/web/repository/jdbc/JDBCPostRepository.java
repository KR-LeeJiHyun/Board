package com.board.web.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.board.web.entity.Post;
import com.board.web.repository.PostForm;
import com.board.web.repository.PostRepository;
import com.board.web.repository.PostSearch;
import com.board.web.repository.UpdatePostForm;

@Repository
public class JDBCPostRepository implements PostRepository {
	
	private DataSource dataSource;

	@Autowired
	public JDBCPostRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int deletePost(String category, Long id) {
		int result = 0;
		String sql = "UPDATE " + category + "_POST SET BLIND = 1 WHERE POST_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1,id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			close(preparedStatement, con);
		}
		
		return result;
	}
	
	@Override
	public List<Post> findAllPostByPostSearch(String category, PostSearch postSearch) {
		String sql = "SELECT * FROM (SELECT ROWNUM NUM, P.* FROM (SELECT * FROM "
				+ category + "_POST" + " WHERE BLIND = 0 ORDER BY " + postSearch.getOrder() + " DESC) P WHERE "
				+ postSearch.getField() + " LIKE ?) WHERE NUM BETWEEN ? AND ?";
		
		final int PAGER = 10;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Post> list = new LinkedList();
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, "%" + postSearch.getQuery() + "%");
			preparedStatement.setInt(2, 1 + (postSearch.getPage() - 1) * PAGER);
			preparedStatement.setInt(3, postSearch.getPage() * PAGER);
			
			rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Long id = rs.getLong("POST_ID");
				String memberId = rs.getString("MEMBER_ID"); 
				String writer = rs.getString("WRITER"); 
				String title = rs.getString("TITLE"); 
				String content = rs.getString("CONTENT"); 
				Date regdate = rs.getTimestamp("REGDATE"); 
				Integer like = rs.getInt("LIKE"); 
				Integer unlike = rs.getInt("UNLIKE"); 
				Integer hit = rs.getInt("HIT"); 
				String subcategory = rs.getString("CATEGORY");  
				
				list.add(new Post(id, memberId, writer, title, content, regdate, like, unlike, hit, subcategory));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, preparedStatement, con);
		}
		return list;
	}
	
	@Override
	public Post findPost(String category, Long id) {
		String sql = "SELECT * FROM " + category + "_POST WHERE POST_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Post post = null;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				String memberId = rs.getString("MEMBER_ID"); 
				String writer = rs.getString("WRITER"); 
				String title = rs.getString("TITLE"); 
				String content = rs.getString("CONTENT"); 
				Date regdate = rs.getDate("REGDATE"); 
				Integer like = rs.getInt("LIKE"); 
				Integer unlike = rs.getInt("UNLIKE"); 
				Integer hit = rs.getInt("HIT"); 
				String subcategory = rs.getString("CATEGORY");  
				post = new Post(id, memberId, writer, title, content, regdate, like, unlike, hit, subcategory);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, preparedStatement, con);
		}
		
		return post;
	}
	
	@Override
	public String findMeberIdByPostId(String category, Long id) {
		String sql = "SELECT MEMBER_ID FROM " + category + "_POST WHERE POST_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String memberId = null;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				memberId = rs.getString("MEMBER_ID"); 			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, preparedStatement, con);
		}
		
		return memberId;
	}
	
	@Override
	public int insertPost(String category, PostForm postForm) {
		String sql = "INSERT INTO " +  category + "_POST(MEMBER_ID, WRITER, TITLE, CONTENT, CATEGORY) VALUES(?,?,?,?,?)";
		
		int result = 0;		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {	
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, postForm.getMemberId());
			preparedStatement.setString(2, postForm.getWriter());
			preparedStatement.setString(3, postForm.getTitle());
			preparedStatement.setString(4, postForm.getContent());
			preparedStatement.setString(5, postForm.getCategory());
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
	public int updatePost(String category, UpdatePostForm updatePostForm) {
		String sql = "UPDATE " +  category + "_POST SET TITLE = ?, CONTENT = ? WHERE POST_ID = ?";
		int result = 0;		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {	
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, updatePostForm.getTitle());
			preparedStatement.setString(2, updatePostForm.getContent());
			preparedStatement.setLong(3, updatePostForm.getPostId());
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
	public int updateCount(String category, Long id, String column) {
		String sql = "UPDATE " +  category + "_POST SET " + column + " = " + column + " + 1 WHERE POST_ID = ?";
		int result = 0;		
		Connection con = null;
		PreparedStatement preparedStatement = null;		
		try {	
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
			result = 0;
		} finally {
			close(preparedStatement, null);
		}
		
		return result;
	}
	
	@Override
	public int findCount(String category, Long id, String column) {
		String sql = "SELECT " + column + " FROM " + category + "_POST WHERE POST_ID = ?";
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(column); 		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, preparedStatement, con);
		}
		
		return cnt;
	}
	
	@Override
	public int findTotalCount(String category) {
		String sql = "SELECT COUNT(POST_ID) AS COUNT FROM " + category + "_POST";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			con = DataSourceUtils.getConnection(this.dataSource);
			preparedStatement = con.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt("COUNT"); 		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, preparedStatement, con);
		}
		
		return cnt;
	}
	
	private void close(PreparedStatement preparedStatement, Connection con) {
		close(null, preparedStatement, con);
	}
	
	private void close(ResultSet rs, PreparedStatement preparedStatement, Connection con) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
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
