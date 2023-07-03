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
	<div id="blackbox"></div>
	<header>
		<div class="header_inner">
			<div class="title_menu_wrap">
				<span class="title"> 제목을 입력하시오 <img class="logo"
					src="/community/images/logo.jpg" />
				</span>
				<div class="user_menu">
					<ul class="menu">
						<li><a class="user_menu_active" href="">HOME</a></li>
						<li><a href="">MY PAGE</a></li>
						<li><a href="">FOLLOW US</a></li>
						<li>
						<c:if test="${member != null}">
							<c:if test="${member.id != null}">
								<form method="post" action="/community/members/logout">
									<input id="logout_button" type="submit" value="LOGOUT">
								</form>
							</c:if>
							<c:if test="${member.id == null}">
								<a href="/community/members/login">
									LOGIN
								</a>
							</c:if>
						</c:if>							
						</li>
					</ul>
				</div>
			</div>
			<div class="category_list_box">
				<ul class="menu category_list">
					<li class="category_list_item">
						<div class="category_box">
							<div class="category category_active">자유게시판</div>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<div class="category">게임</div>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
							<div>직업 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<div class="category">스포츠</div>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<div class="category">애완동물</div>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
							<div>직업 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<div class="category">IT/금융</div>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</header>
	<div class="container">
		<div class="content_banner">
			<h1>자유게시판</h1>
		</div>
		<header>
			<div class="post_header">
				<h3>${post.title}</h3>
				<div class="post_info_box">
					<div>
						<span>휴지</span> <span> | </span> <span>2022-11-21 14:35:07</span>
					</div>
					<div>
						<span>조회수 126</span> <span> | </span> <span>좋아요 2187</span> <span>
							| </span> <span>싫어요 2</span> <span> | </span> <span>댓글수 12</span>
					</div>
				</div>
				<div id ="category" class="hidden">${post.category}</div>
				<div id ="post_id" class="hidden">${post.postId}</div>
				
			</div>
		</header>
		<div class="post_content">
			${post.content}
		</div>
		<div class="like_unlike_wrap">
			<button class="btn_like_unlike btn_like_unlike_big">👍</button>
			<button class="btn_like_unlike btn_like_unlike_big">👎</button>
		</div>
		
		<div class="comment_box">
			
		</div>
		
	</div>
	<script src="/community/js/jquery.js"></script>
	<script src="/community/js/resize.js"></script>
	<script src="/community/js/comment.js"></script>	
</body>
</html>