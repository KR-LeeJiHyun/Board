package com.board.web.controller.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.web.entity.Comment;
import com.board.web.entity.Member;
import com.board.web.repository.jdbc.comment.CommentDTO;
import com.board.web.service.comment.CommentDTONode;
import com.board.web.service.comment.CommentService;

@Controller
@RequestMapping("/board/{category}/{postId}/comment/")
public class CommentController {
	
	private final CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/create")
	public String createComment(@PathVariable String category, @PathVariable Long postId, String content, Model model, HttpServletRequest request) {
		Member member = getMemberFromSession(request);
		if (member == null || member.getId() == null) {
			return "redirect:/members/login?redirectURL=/board/" + category + "/" + postId;
		}
		
		model.addAttribute("member", member);
		CommentForm commentForm = new CommentForm(postId, 0L, category, content);
		commentService.createComment(member, commentForm);
		long topTotal = commentService.findTopCommentTotal(category, postId);
		int last = (int)(topTotal % CommentConst.PAGER == 0 ? topTotal / CommentConst.PAGER : topTotal / CommentConst.PAGER + 1);
		return "redirect:/board/" + category + "/" + postId + "/comment/list?page=" + 1;
	}
	
	@PostMapping("/reply/{parentId}")
	public String createReplyComment(@PathVariable String category, @PathVariable Long postId, @PathVariable Long parentId, 
			String content, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Member member = getMemberFromSession(request);
		if (member == null || member.getId() == null) {
			// sendError 필요
			return "";
		}
		
		model.addAttribute("member", member);
		CommentForm commentForm = new CommentForm(postId, parentId, category, content);
		Comment comment = commentService.createComment(member, commentForm);
		CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getMemberId(), comment.getWriter(), comment.getContent(), comment.getRegdate(), comment.getLike(), comment.getUnlike(), 0, comment.getBlind());
		model.addAttribute("comment", commentDTO);
		return "replyComment";
	}
	
	@GetMapping("/list")
	public String list(@PathVariable String category, @PathVariable Long postId, int page, Model model, HttpServletRequest request) {
		Member member = getMemberFromSession(request);
		model.addAttribute("member", member);
		
		long total = commentService.findCommentTotal(category, postId);
		long topTotal = commentService.findTopCommentTotal(category, postId);
		List<CommentDTO> list = commentService.findComments(category, postId, page);
		
		int last = (int)(topTotal % CommentConst.PAGER == 0 ? topTotal / CommentConst.PAGER : topTotal / CommentConst.PAGER + 1);
		int begin = ((int)Math.ceil((double)page / 5) - 1) * 5 + 1; 
		int end = last <= begin + 4 ? last : begin + 4;
		
		model.addAttribute("commentTotal", total);
		model.addAttribute("commentList", list);
		model.addAttribute("page", page);
		model.addAttribute("category", category);
		model.addAttribute("postId", postId);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("lastPage", last);
		return "commentList";
	}
	
	@GetMapping("/reply/{commentId}")
	public String reply(@PathVariable String category, @PathVariable Long postId, @PathVariable long commentId, Model model, HttpServletRequest request) {
		Member member = getMemberFromSession(request);
		model.addAttribute("member", member);
		
		List<CommentDTONode> list = commentService.findReplyComments(category, postId, commentId);
		model.addAttribute("list", list);
		return "replyCommentList";
	}

	@ResponseBody
	@PostMapping("/count")
	public int like(@PathVariable String category, String column, Long commentId) {
		return commentService.updateCount(category, column.toUpperCase(), commentId);
	}
	
	@PostMapping("/update/{commentId}")	
	public String update(@PathVariable String category,  @PathVariable Long commentId, String content, Model model, HttpServletRequest request, HttpServletResponse response) {
		Member member = getMemberFromSession(request);
		if (member == null) {
//			response.sendError("");
		}
		
		CommentDTO comment = commentService.updateContent(member, category, commentId, content);
		if (comment == null) {
//			response.sendError("");
		}
		
		model.addAttribute("comment", comment);
		model.addAttribute("member", member);
		return "comment";
	}
	
	@PostMapping("/delete/{commentId}")
	public void delete(@PathVariable String category, @PathVariable Long commentId, HttpServletRequest request, HttpServletResponse response) {
		Member member = getMemberFromSession(request);
		if (member == null) {
//			response.sendError("");
		}
		
		int result = commentService.deleteComment(member, category, commentId);
		if (result == 1) {
//			response.sendError("");
		}
	}
	
	private Member getMemberFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Member member = (Member) session.getAttribute("member");
		return member;
	}
}
