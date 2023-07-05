package com.board.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.board.web.repository.PostSearch;
import com.board.web.service.PostService;

@Controller
@RequestMapping("/board/{category}")
public class PostController {
	
	private PostService postService;
	
	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	//게시글 목록
	@GetMapping
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
	@GetMapping(value = {"/{id}"})
	public String detail(HttpServletRequest request, @PathVariable String category, @PathVariable Long id, Model model) {
		Post post = postService.findPost(category, id);
		model.addAttribute("post", post);
		HttpSession session = request.getSession(false);
        if(session != null) {
        	Member member = (Member)session.getAttribute("member");
        	if(member != null) {        		
        		model.addAttribute("member", member);
        	}
        }
		return "detail";
	}
	
	//게시글 작성 페이지 조회
	@GetMapping(value = {"/new"})
	public String createForm(@PathVariable String category, Model model) {
		return "detail";
	}
	
	//게시글 작성
	@PostMapping(value = {"/new"})
	public String createPost(@PathVariable String category, Model model) {
		return "redirect:/board/" + category;
	}
	
	//게시글 수정 페이지 조회
	@GetMapping(value = {"/{id}/edit"})
	public String updateForm(@PathVariable String category, @PathVariable Long id, Model model) {
		return "detail";
	}
		
	//게시글 수정
	@PostMapping(value = {"/{id}/edit"})
	public String updatePost(@PathVariable String category, @PathVariable Long id, Model model) {
		return "redirect:/board/" + category + "/" + id;
	}
	
	//게시글 좋아요
	@PostMapping(value = {"/{id}/like"})
	@ResponseBody
	public int updateLike(@PathVariable String category, @PathVariable Long id, Model model) {
		return postService.updateCount("LIKE", category, id);
	}
	
	//게시글 싫어요
	@PostMapping(value = {"/{id}/unlike"})
	@ResponseBody
	public int updateUnlike(@PathVariable String category, @PathVariable Long id, Model model) {
		return postService.updateCount("UNLIKE", category, id);
	}
	
	//게시글 삭제
	@PostMapping(value = {"/{id}/delete"})
	public String deletePost(@PathVariable String category, @PathVariable Long id) {
		return "redirect:/board/" + category;
	}
	
	
}

