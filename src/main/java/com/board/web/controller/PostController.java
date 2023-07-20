package com.board.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.entity.Member;
import com.board.web.entity.Post;
import com.board.web.repository.PostForm;
import com.board.web.repository.PostSearch;
import com.board.web.repository.UpdatePostForm;
import com.board.web.service.PostService;

@Controller
@RequestMapping("/board")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	//게시글 목록
	@GetMapping("/{category}")
	public String home(@PathVariable String category, String field, String query, String order, Integer page, Model model) {
		PostSearch postSearch = new PostSearch();
		
		if(field != null) {
			postSearch.setField(field);
		}
		if(query != null) {
			postSearch.setQuery(query);
		}
		if(order != null) {
			postSearch.setOrder(order);
		}
		if(page != null) {
			postSearch.setPage(page);
		}
		
		int lastPage = (int)Math.ceil((double)postService.findTotalCount(category) / 10);
		int begin = ((int)Math.ceil((double)postSearch.getPage() / 5) - 1) * 5 + 1;
		int end = begin + 4;
		List<Post> postList = postService.findPosts(category, postSearch);
		model.addAttribute("postList", postList);
		model.addAttribute("category", category);
		model.addAttribute("page", postSearch.getPage());
		model.addAttribute("begin", begin);
		model.addAttribute("end", Math.min(lastPage, end));
		model.addAttribute("lastPage", lastPage);
		
		return "index";
	}
	
	//게시글 상세
	@GetMapping(value = {"/{category}/{id}"})
	public String detail(HttpServletRequest request,HttpServletResponse response, @PathVariable String category, @PathVariable Long id, Model model) {
		Post post = postService.findPost(category, id);
		model.addAttribute("category", category);
		model.addAttribute("post", post);
		HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("member");
        	if(member != null) {        		
        		model.addAttribute("memberId", member.getId());
        	}
        }
        
		return "detail";
	}
	
	//게시글 작성 페이지 조회
	@GetMapping(value = {"/new/{category}"})
	public String createForm(HttpServletRequest request, @PathVariable String category, Model model) {
		HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("member");
        	if(member != null && member.getId() != null) {
        		return "write";
        	}
        	else {
        		return "redirect:/members/login";
        	}
        }
        else {
        	return "redirect:/members/login";
        }
        
	}
	
	//게시글 작성
	@PostMapping(value = {"/new/{category}"})
	public String createPost(HttpServletRequest request, @PathVariable String category, String title, String content, Model model) {
		HttpSession session = request.getSession(false);
		Member member = null;
        if(session != null) {
        	member = (Member)session.getAttribute("member");
        	if(member == null || member.getId() == null) {
        		return "redirect:/members/login";
        	}
        }
        else {
        	return "redirect:/members/login";
        }
        
        PostForm postForm = new PostForm(member.getId(), member.getNickname(), title, content, category);
        postService.savePost(category, postForm);
        
		return "redirect:/board/" + category;
	}
	
	//게시글 수정 페이지 조회
	@GetMapping(value = {"/edit/{category}/{id}"})
	public String updateForm(HttpServletRequest request, @PathVariable String category, @PathVariable Long id, Model model) {
		HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("member");
        	if(member != null && member.getId() != null) {
        		Post post = postService.findPost(category, id);
        		model.addAttribute("post", post);
        		return "edit";
        	}
        	else {
        		return "redirect:/members/login";
        	}
        }
        else {
        	return "redirect:/members/login";
        }
	}
		
	//게시글 수정
	@PostMapping(value = {"/edit/{category}/{id}"})
	public String updatePost(HttpServletRequest request, @PathVariable String category, @PathVariable Long id, Model model, String title, String content) {
		HttpSession session = request.getSession(false);
		Member member = null;
        if(session != null) {
        	member = (Member)session.getAttribute("member");
        	if(member == null || member.getId() == null) {
        		return "redirect:/members/login";
        	}
        }
        else {
        	return "redirect:/members/login";
        }
        
		UpdatePostForm updatePostForm = new UpdatePostForm(id, member.getId(), title, content);
		postService.updatePost(category, updatePostForm);
		return "redirect:/board/" + category + "/" + id;
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
		HttpSession session = request.getSession(false);
		Member member = null;
        if(session != null) {
        	member = (Member)session.getAttribute("member");
        	if(member == null || member.getId() == null) {
        		return "redirect:/members/login";
        	}
        }
        else {
        	return "redirect:/members/login";
        }
        
		postService.deletePost(member.getId(),category, id);
		return "redirect:/board/" + category;
	}
	
	
}

