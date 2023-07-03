<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="comment_header">
    전체 댓글 수 <span>${commentTotal}</span>개
</div>
<ul id ="comment_list" class="comment_list">
    <c:if test="${commentList != null}">
    <c:forEach var="comment" items="${commentList}">
    <li class="comment_group">
        <div id="${comment.id}" class="comment_item">
            <div class="comment_area">
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
                        <button class="btn_like_unlike btn_like_unlike_small" onclick="count(this, 'like')">👍</button><span class="like like_unlike_count">${comment.like}</span>
                        <button class="btn_like_unlike btn_like_unlike_small" onclick="count(this, 'unlike')">👎</button><span class="unlike like_unlike_count">${comment.unlike}</span>
                        <button class="comment_btn" onclick="toggleReplyWriter(this)">답글</button>
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
                    
                    <div class="comment_writer hidden">
                        <div class="nickname">${comment.writer}</div>		
                        <textarea class="comment_input_area" rows="1" placeholder="댓글을 입력하세요"
                        onkeyup="resize(this)"></textarea>
                        <div class="comment_btn_box">
                            <button class="comment_btn reply_comment_btn" onclick="addReplyComment(this)">댓글 입력</button>
                        </div>
                    </div>
                    </c:if>
                    
                    <button class="comment_btn blank_bottom reply_trigger" onclick="loadReply(this)"
                        url="/community/board/${category}/${postId}/comment/reply/${comment.id}" first>답글 보기</button>
                    <!-- 대댓글 -->
                    <div class="comment_reply_list">
                    </div>
                </div>
            </div>
<!-- 
            <div class="comment_wrap">
                <div class="comment_area">
                                    
                </div>
    
                
            </div> -->
        </div>        					
    </li>
    </c:forEach>
    </c:if>
</ul>
<div class="page_box">
    <ul>
        <c:if test="${begin > 1}">
        <li id="prev" class="page" onclick="loadComment('${category}', ${postId}, ${begin - 1})">&lt;</li>
        </c:if>
        <c:forEach begin="${begin}" end="${end}" varStatus="st">
        <li class="page" name="${st.index}" onclick="loadComment('${category}', ${postId}, ${st.index})">
            <c:if test="${st.index == page}">
                <strong>${st.index}</strong>
            </c:if>
            <c:if test="${st.index != page}">
                ${st.index}
            </c:if>
        </li>
        </c:forEach>
        <c:if test="${lastPage > end}">
        <li id="next"  class="page" onclick="loadComment('${category}', ${postId}, ${end + 1})">&gt;</li>        
        </c:if>
    </ul>
</div>
<div class="comment_writer">
    <div class="nickname">화난소</div>
    <!-- <textarea class="comment_input_area" placeholder="댓글을 입력하세요"
        onkeydown="resize(this)" onkeyup="resize(this)"></textarea> -->
    <textarea class="comment_input_area" placeholder="댓글을 입력하세요" onkeydown="resize(this)"></textarea>
    <div class="comment_btn_box">
        <button class="comment_btn main_comment_btn" onclick="addComment(this)">댓글 입력</button>
    </div>
</div>
