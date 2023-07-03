<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${comment.blind == 1}">
<div class="comment">
    <div class="comment_content">삭제된 댓글입니다</div>
</div>
</c:if>
<c:if test="${comment.blind == 0}">
<div class="comment">
    <div class="nickname">${comment.writer}</div>
    <div class="comment_content">${comment.content}</div>
    <div class="comment_date">${comment.regdate}</div>
    <button class="btn_like_unlike btn_like_unlike_small" onclick="count(this, 'like')">👍</button><span class="like_unlike_count">${comment.like}</span>
    <button class="btn_like_unlike btn_like_unlike_small" onclick="count(this, 'unlike')">👎</button><span class="like_unlike_count">${comment.unlike}</span>
    <button class="comment_btn reply_writer_trigger" onclick="toggleReplyWriter(this)">답글</button>
    <c:if test="${member != null && member.id == comment.memberId}">
    <button class="comment_btn edit_writer_trigger" onclick="switchCommentToEditor(this)">수정</button>
    <button class="comment_btn delete_comment_btn" onclick="deleteComment(this)">삭제</button>
    </c:if>
</div>
</c:if>