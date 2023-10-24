<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/community/css/header.css">

	<div id="blackbox"></div>
	<header>
		<div class="header_inner">
			<div class="title_menu_wrap">
				<span class="title"> 서리 <img class="logo"
					src="/community/images/logo.jpg" />
				</span>
				<div class="user_menu">
					<ul class="menu">
						<li><a class="user_menu_active" href="">HOME</a></li>
						<li><a class="menu" href="/community/members/mypage">MY PAGE</a></li>
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
								<div class="category">
									<a href="/community/board/free"
									class=${category == "free" ? "category_active" : ""} 
									>자유게시판</a>
								</div>
						</div>
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
							<div><a href="/community/board/game/online">온라인</a></div>
							<div><a href="/community/board/game/mobile">모바일</a></div>
							<div><a href="/community/board/game/console">콘솔</a></div>
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
							<div><a href="/community/board/sports/soccer">축구</a></div>
							<div><a href="/community/board/sports/baseball">야구</a></div>
							<div><a href="/community/board/sports/basketball">농구</a></div>
							<div><a href="/community/board/sports/etc">기타</a></div>
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
							<div><a href="/community/board/pet/dog">강아지</a></div>
							<div><a href="/community/board/pet/cat">고양이</a></div>
							<div><a href="/community/board/pet/etc">기타</a></div>
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
							<div><a href="/community/board/it_finance/it">IT</a></div>
							<div><a href="/community/board/it_finance/finance">금융</a></div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</header>