<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>index</title>
<link rel="stylesheet" href="/community/css/index.css">
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
						<li><a class="menu" href="">MY PAGE</a></li>
						<li><a class="menu" href="">FOLLOW US</a></li>
						<li><c:if test="${member.id != null}">
								<form method="post" action="/community/members/logout">
									<input id="logout_button" type="submit" value="LOGOUT">
								</form>
							</c:if> <c:if test="${member.id == null}">
								<a class="menu" href="/community/members/login"> LOGIN </a>
							</c:if></li>
					</ul>
				</div>
			</div>
			<div class="category_list_box">
				<ul class="menu category_list">
					<li class="category_list_item">
						<div class="category_box">
							<c:if test="${category == 'free'}">
								<div class="category category_active">자유게시판</div>
							</c:if>
							<c:if test="${category != 'free'}">
								<div class="category">
									<a href="/community/board/free">자유게시판</a>
								</div>
							</c:if>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
						</div>
						<div class="invisible"></div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<c:if test="${category == 'game'}">
								<div class="category category_active">게임</div>
							</c:if>
							<c:if test="${category != 'game'}">
								<div class="category">
									<a href="/community/board/game">게임</a>
								</div>
							</c:if>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
							<div>직업 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<c:if test="${category == 'sports'}">
									<div class="category category_active">스포츠</div>
							</c:if>
							<c:if test="${category != 'sports'}">
								<div class="category">
									<a href="/community/board/sports">스포츠</a>
								</div>
							</c:if>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<c:if test="${category == 'pet'}">
								<div class="category category_active">애완동물</div>
							</c:if>
							<c:if test="${category != 'pet'}">
								<div class="category">
									<a href="/community/board/pet">애완동물</a>
								</div>
							</c:if>
						</div>
						<div class="category_detail">
							<div>화제 게시판</div>
							<div>자유 게시판</div>
							<div>직업 게시판</div>
						</div>
					</li>
					<li class="category_list_item">
						<div class="category_box">
							<c:if test="${category == 'it_finance'}">
									<div class="category category_active">IT/금융</div>
							</c:if>
							<c:if test="${category != 'it_finance'}">
									<div class="category">
										<a href="/community/board/it_finance">IT/금융</a>
									</div>
							</c:if>
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
				</select> <a href="new/${category}"> <i class="fa-solid fa-pencil"></i>
				</a>
			</div>
		</div>
		<div class="content">
			<table class="board">
				<colgroup>
					<col class="post_num" style="width:80px">
					<col>
					<col style="width:200px">
					<col style="width:80px">
					<col style="width:80px">
					<col style="width:200px">
				</colgroup>
				<tr>
					<th>NO.</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>날짜</th>
				</tr>

				<c:forEach var="post" items="${postList}">
					<tr>
						<td>${post.postId}</td>
						<td><a href="/community/board/${category}/${post.postId}">${post.title}</a><span
							class="ballon">12</span></td>
						<td>${post.writer}</td>
						<td>${post.hit}</td>
						<td>${post.like}</td>
						<td>
							<fmt:formatDate value="${post.regdate}" type="both" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="page_box">
				<ul>
					<c:if test="${begin > 1}">
						<a href="?page=${begin - 1}">&lt;</a>
					</c:if>
					<c:forEach begin="${begin}" end="${end}" varStatus="st">
							<c:if test="${st.index == page}">
								<li class="page_active">${st.index}</li>
							</c:if> 
							<c:if test="${st.index != page}">
								<a href="?page=${st.index}">
               						${st.index}
               					</a>
            				</c:if>
						
					</c:forEach>
					<c:if test="${lastPage > end}">
						<a href="?page=${end + 1}" >&gt;</a>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>