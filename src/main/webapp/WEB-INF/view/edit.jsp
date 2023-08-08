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
<link rel="stylesheet" href="/community/css/edit.css">

<script src="https://kit.fontawesome.com/012cf477c7.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="content_banner">
			<h1>자유게시판</h1>
		</div>
		<form action="edit" method="post">
		<header>
			<div class="post_header">
				<input class="post_title" name="title" placeholder="제목을 입력해 주세요." value="${post.title}"/>
				<div class="post_info_box">
					<div>
						<span>${post.writer}</span> <span> | </span> <span>${post.regdate}</span>
					</div>
					<div>
						<span>조회수 ${post.hit}</span> <span> | </span> <span>좋아요 ${post.like}</span> <span>
							| </span> <span>싫어요 ${post.unlike}</span>
					</div>
				</div>
			</div>
		</header>
		<div class="post_content">
			<textarea name="content" class="post_content" placeholder="내용을 입력해 주세요." style="white-space: pre-wrap;">${post.content}</textarea>
		</div>
		<input class="write_btn" type="button" value="취소"></input>
		<input class="write_btn" type="submit" value="제출"></input>
		</form>
	</div>
	<script src="/community/js/resize.js"></script>
	<!-- <script src="test.js"></script> -->
</body>
</html>