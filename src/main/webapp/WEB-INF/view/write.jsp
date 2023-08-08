<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세</title>
<link rel="stylesheet" href="/community/css/write.css">

<script src="https://kit.fontawesome.com/012cf477c7.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="content_banner">
			<h1>자유게시판</h1>
		</div>
			<form action="/community/board/new/${category}" method="post">
			<header>
				<input name="title" class="post_header" placeholder="제목을 입력해 주세요."/>
			</header>
			<textarea name="content" class="post_content" placeholder="내용을 입력해 주세요."></textarea>
			<input class="write_btn" type="button" value="취소"></input>
			<input class="write_btn" type="submit" value="제출"></input>
		</form>		
	</div>
</body>
</html>