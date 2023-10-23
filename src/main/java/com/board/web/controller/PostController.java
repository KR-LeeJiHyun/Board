package com.board.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.common.LoginCheck;
import com.board.web.entity.Member;
import com.board.web.entity.Post;
import com.board.web.repository.PostAllSearch;
import com.board.web.repository.PostForm;
import com.board.web.repository.PostSearch;
import com.board.web.repository.UpdatePostForm;
import com.board.web.service.PostService;
import com.board.web.service.comment.CommentService;

@Controller
@RequestMapping("/board")
public class PostController {
	
	private PostService postService;
	private CommentService commentService;
	
	@Autowired
	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	//게시글 목록
	@GetMapping("/{category}")
	public String home(@PathVariable String category, 
			@RequestParam(defaultValue = "TITLE") String field, @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "REGDATE")String order, 
			@RequestParam(defaultValue = "1")Integer page, Model model) {
		PostAllSearch postAllSearch = new PostAllSearch(field, query, order, category, page);
		
		int lastPage = (int)Math.ceil((double)postService.findTotalCount(category) / 10);
		int begin = ((int)Math.ceil((double)postAllSearch.getPage() / 5) - 1) * 5 + 1;
		int end = begin + 4;
		List<Post> postList = postService.findPosts(postAllSearch);
		List<Long> postIds = new ArrayList<>();
		for(Post post : postList) {
			postIds.add(post.getPostId());
		}
		List<Integer> commentCntList = commentService.findCommentCounts(postIds);
		model.addAttribute("postList", postList);
		model.addAttribute("category", category);
		model.addAttribute("page", postAllSearch.getPage());
		model.addAttribute("begin", begin);
		model.addAttribute("end", Math.min(lastPage, end));
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("commentCntList", commentCntList);
		return "index";
	}
	
	//게시글 세부 목록
	@GetMapping("/{category}/{subCategory}")
	public String detailHome(@PathVariable String category, @PathVariable String subCategory, 
			@RequestParam(defaultValue = "TITLE") String field, @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "REGDATE")String order, 
			@RequestParam(defaultValue = "1")Integer page, Model model) {
		PostSearch postSearch = new PostSearch(field, query, order, category, subCategory, page);
		
		int lastPage = (int)Math.ceil((double)postService.findTotalCount(category, subCategory) / 10);
		int begin = ((int)Math.ceil((double)postSearch.getPage() / 5) - 1) * 5 + 1;
		int end = begin + 4;
		List<Post> postList = postService.findPosts(postSearch);
		List<Long> postIds = new ArrayList<>();
		for(Post post : postList) {
			postIds.add(post.getPostId());
		}
		List<Integer> commentCntList = commentService.findCommentCounts(postIds);
		model.addAttribute("postList", postList);
		model.addAttribute("postList", postList);
		model.addAttribute("category", category);
		model.addAttribute("page", postSearch.getPage());
		model.addAttribute("begin", begin);
		model.addAttribute("end", Math.min(lastPage, end));
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("commentCntList", commentCntList);
		return "index";
	}
	
	//게시글 상세
	@GetMapping(value = {"/{category}/{subCategory}/{id}"})
	public String detail(HttpServletRequest request,HttpServletResponse response, @PathVariable String category, @PathVariable Long id, Model model) {
		Post post = postService.findPost(category, id);
		model.addAttribute("category", category);
		model.addAttribute("post", post);
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			model.addAttribute("memberId", member.getId());
		}
		return "detail";
	}
	
	//게시글 작성 페이지 조회
	@GetMapping(value = {"/new/{category}"})
	public String createForm(HttpServletRequest request, @PathVariable String category, Model model) {
		if(LoginCheck.isLoggedIn(LoginCheck.getMemberFromSession(request))) {
    		model.addAttribute("category", category);
    		return "write";
    	}
    	else {
    		return "redirect:/members/login";
    	}  
	}
	
	//게시글 작성
	@PostMapping(value = {"/new/{category}"})
	public String createPost(HttpServletRequest request, @PathVariable String category, String subCategory, String title, String content, Model model) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			PostForm postForm = new PostForm(member.getId(), member.getNickname(), title, content, subCategory);
	        postService.savePost(category, postForm);
	        
			return "redirect:/board/" + category;
		}
		else {
			return "redirect:/members/login";
		}
	}
	
	//게시글 수정 페이지 조회
	@GetMapping(value = {"/edit/{category}/{id}"})
	public String updateForm(HttpServletRequest request, @PathVariable String category, @PathVariable Long id, Model model) {
		if(LoginCheck.isLoggedIn(LoginCheck.getMemberFromSession(request))) {
			Post post = postService.findPost(category, id);
    		model.addAttribute("post", post);
    		return "edit";
    	}
    	else {
    		return "redirect:/members/login";
    	} 
	}
		
	//게시글 수정
	@PostMapping(value = {"/edit/{category}/{id}"})
	public String updatePost(HttpServletRequest request, @PathVariable String category, @PathVariable Long id, Model model, String title, String content) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			UpdatePostForm updatePostForm = new UpdatePostForm(id, member.getId(), title, content);
			postService.updatePost(category, updatePostForm);
			return "redirect:/board/" + category + "/" + id;
		}
		else {
			return "redirect:/members/login";
		}
	}
	
	//게시글 좋아요
	@PostMapping(value = {"/like/{category}/{id}"})
	@ResponseBody
	public int updateLike(@PathVariable String category, @PathVariable Long id, Model model) {
		return postService.updateCount("LIKE", category, id);
	}
	
	//게시글 싫어요
	@PostMapping(value = {"/unlike/{category}/{id}"})
	@ResponseBody
	public int updateUnlike(@PathVariable String category, @PathVariable Long id, Model model) {
		return postService.updateCount("UNLIKE", category, id);
	}
	
	//게시글 삭제
	@PostMapping(value = {"/delete/{category}/{id}"})
	public String deletePost(HttpServletRequest request, @PathVariable String category, @PathVariable Long id) {
		Member member = LoginCheck.getMemberFromSession(request);
		if(LoginCheck.isLoggedIn(member)) {
			postService.deletePost(member.getId(),category, id);
			return "redirect:/board/" + category;
    	}
    	else {
    		return "redirect:/members/login";
    	} 
	}
	
	
}

