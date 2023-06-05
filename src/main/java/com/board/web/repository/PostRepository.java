package com.board.web.repository;

import java.util.List;

import com.board.web.entity.Post;

public interface PostRepository {
	List<Post> findAllPostByPostSearch(String category, PostSearch postSearch);
	Post findPost(String category, Long id);
	int insertPost(String category, PostForm postForm);
	int updatePost(String category, UpdatePostForm updatePostForm);
	int updateCount(String category, Long id, String column);
	int deletePost(String category, Long id);
	int findCount(String category, Long id, String column);
	String findMeberIdByPostId(String category, Long id);
}
