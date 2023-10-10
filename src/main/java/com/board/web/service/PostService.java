package com.board.web.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.board.web.entity.Post;
import com.board.web.repository.PostAllSearch;
import com.board.web.repository.PostForm;
import com.board.web.repository.PostRepository;
import com.board.web.repository.PostSearch;
import com.board.web.repository.UpdatePostForm;

@Service
public class PostService {
	private DataSource dataSource;
	private PostRepository postRepository;
	
	@Autowired
	public PostService(DataSource dataSource, PostRepository postRepository) {
		this.dataSource = dataSource;
		this.postRepository = postRepository;
	}
	
	public int savePost(String category, PostForm postForm) {
		return postRepository.insertPost(category, postForm);
	}
	
	public int updatePost(String category, UpdatePostForm updatePostForm) {
		if(isPossible(updatePostForm.getMemberId(),category, updatePostForm.getPostId())) {
			return postRepository.updatePost(category, updatePostForm);
		}
		return -1;
	}
	
	public List<Post> findPosts(PostAllSearch postAllSearch) {
		return postRepository.findPosts(postAllSearch);
	}
	
	public List<Post> findPosts(PostSearch postSearch) {
		return postRepository.findPosts(postSearch);
	}
	
	public Post findPost(String category, Long id) {
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Post post = null;
        
        try{
        	postRepository.updateCount(category, id, "HIT");
        	post = postRepository.findPost(category, id);
        	transactionManager.commit(status);
        } catch(TransactionException e) {
        	post = null;
        	transactionManager.rollback(status);
        }
        
		return post;
	}
	
	public int deletePost(String memberId, String category, Long id) {
		if(isPossible(memberId, category, id)) {
			return postRepository.deletePost(category, id);
		}
		return -1;
	}
	
	public int updateCount(String column, String category, Long id) {
		postRepository.updateCount(category, id, column);
		return postRepository.findCount(category, id, column);
	}
	
    public boolean isPossible(String memberId, String category, Long id) {
    	return memberId.equals(postRepository.findMeberIdByPostId(category, id));
    }
    
    public int findTotalCount(String category) {
    	return postRepository.findTotalCount(category);
    }
    
    public int findTotalCount(String category, String subCategory) {
    	return postRepository.findTotalCount(category, subCategory);
    }

}
