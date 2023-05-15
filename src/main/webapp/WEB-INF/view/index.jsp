<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
<link rel="stylesheet" href="css/index.css">
<script src="https://kit.fontawesome.com/012cf477c7.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<div id="blackbox"></div>
	<header>
		<div class="header_inner">
			<div class="title_menu_wrap">
				<span class="title"> 제목을 입력하시오 <img class="logo"
					src="images/logo.jpg" />
				</span>
				<div class="user_menu">
					<ul class="menu">
						<li><a class="user_menu_active" href="">HOME</a></li>
						<li><a href="">MY PAGE</a></li>
						<li><a href="">FOLLOW US</a></li>
						<li>
							<c:if test="${member.id != null}">
								<a href="/community/members/logout">
        							LOGOUT
        						</a>
    						</c:if>
    						<c:if test="${member.id == null}">
        						<a href="/community/members/login">
        							LOGIN
        						</a>
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
						<div class="invisible"></div>
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
			<form class="search_form">
				<select>
					<option>제목</option>
					<option>글쓴이</option>
				</select> <input id="search_content" type="text">
				<button class="search_button">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
			<div class="order_edit_wrap">
				<select class="order">
					<option>조회수 순</option>
					<option>날짜 순</option>
				</select> <a href="#"> <i class="fa-solid fa-pencil"></i>
				</a>
			</div>
		</div>
		<div class="content">
			<table class="board">
				<colgroup>
					<col class="post_num">
					<col class="post_title">
				</colgroup>
				<tr>
					<th>NO.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>날짜</th>
				</tr>
				<tr>
					<td>11</td>
					<td>디자인이 뭐임 ㅈㄱㄴ <span class="ballon">12</span>
					</td>
					<td>휴지</td>
					<td>126</td>
					<td>2187</td>
					<td>5분전</td>
				</tr>
			</table>
			<div class="page_box">
				<ul>
					<li id="prev">&lt;</li>
					<li class="page_active">1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
					<li id="next">&gt;</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>