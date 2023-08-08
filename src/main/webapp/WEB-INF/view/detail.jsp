<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세</title>
<link rel="stylesheet" href="/community/css/detail.css">
<link rel="stylesheet" href="/community/css/comment.css">

<script src="https://kit.fontawesome.com/012cf477c7.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="content_banner">
			<h1>자유게시판</h1>
		</div>
		<header>
			<div class="post_header">
				<h3><c:out value="${post.title}" escapeXml="true" /></h3>
				<div class="post_info_box">
					<div>
						<span>${post.writer}</span> <span> | </span> <span>${post.regdate}</span>
					</div>
					<div>
						<span>조회수 ${post.hit}</span> <span> | </span> <span>좋아요 ${post.like}</span> <span>
							| </span> <span>싫어요 ${post.unlike}</span> <span> | </span>
					</div>
				</div>
				<div id ="category" class="hidden">${post.category}</div>
				<div id ="post_id" class="hidden">${post.postId}</div>
				
			</div>
		</header>
		<div class="post_content" style="white-space: pre">${post.content}</div>
		<div class="like_unlike_wrap">
			<button class="btn_like_unlike btn_like_unlike_big">👍</button>
			<button class="btn_like_unlike btn_like_unlike_big">👎</button>
		</div>
		<c:if test="${memberId == post.memberId}">
			<div class="btn_box">
				<a href="/community/board/edit/${category}/${post.postId}" class="comment_btn">수정</a>
				<a href="/community/board/delete/${category}/${post.postId}" class="comment_btn">삭제</a>
			</div>
		</c:if>
		
		<div class="comment_box">
		</div>
		
	</div>
	<script src="/community/js/jquery.js"></script>
	<script src="/community/js/resize.js"></script>
	<script src="/community/js/comment.js"></script>	
</body>
</html>