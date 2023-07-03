<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${node != null}">
<c:set var="comment" value="${node.commentDTO}" />
</c:if>
<div id="${comment.id}" class="comment_item">
    <div class="comment_area">
        <div class="comment_reply_symbol">
                └
        </div>
        <div class="comment_wrap">
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

            <c:if test="${member != null && member.id == comment.memberId}">
                <div class="comment_editor hidden">
                    <div class="nickname">${comment.writer}</div>		
                    <textarea class="comment_input_area" rows="1" placeholder="댓글을 입력하세요"
                    onkeyup="resize(this)"></textarea>
                    <div class="comment_btn_box">
                        <button class="comment_btn cancel_comment_btn" onclick="cancelEditComment(this)">취소</button>
                        <button class="comment_btn edit_comment_btn" onclick="editComment(this)">댓글 수정</button>
                    </div>
                </div>
            </c:if>
            
            <div class="comment_writer reply_comment_writer hidden">
                <div class="nickname">${comment.writer}</div>
                <textarea name="content" class="comment_input_area" rows="1" placeholder="댓글을 입력하세요"
                    onkeydown="resize(this)" onkeyup="resize(this)"></textarea>
                <div class="comment_btn_box">
                    <button class="comment_btn reply_comment_btn"  onclick="addReplyComment(this)">댓글 입력</button>
                </div>
            </div>
            </c:if>
            
            <div class="comment_reply_list">
                <c:forEach var="node" items="${node.children}">    
                <c:set var="node" value="${node}" scope="request" />
                <jsp:include page="replyComment.jsp" />
                </c:forEach>
            </div> 
        </div>
    </div>
</div>