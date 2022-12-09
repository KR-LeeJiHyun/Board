<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세</title>
<link rel="stylesheet" href="css/detail.css">

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
						<li><a href="">LOGOUT</a></li>
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
				<h3>디자인이 뭐임 ㅈㄱㄴ</h3>
				<div class="post_info_box">
					<div>
						<span>휴지</span> <span> | </span> <span>2022-11-21 14:35:07</span>
					</div>
					<div>
						<span>조회수 126</span> <span> | </span> <span>좋아요 2187</span> <span>
							| </span> <span>싫어요 2</span> <span> | </span> <span>댓글수 12</span>
					</div>
				</div>
			</div>
		</header>
		<div class="post_content">
			안녕하세요 ㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴ<br> ㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴ<br>
			ㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴ<br> ㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴㅈㄱㄴ<br>
		</div>
		<div class="like_unlike_wrap">
			<button class="btn_like_unlike">👍</button>
			<button class="btn_like_unlike">👎</button>
		</div>
		<div class="comment_box">
			<div class="comment_header">
				<span>전체 댓글 수 12개</span>
			</div>
			<ul class="comment_list">
				<li class="comment_item">
					<div class="comment_area">
						<div class="nickname">코뿔소</div>
						<div class="comment_txt">안녕하세요</div>
						<div class="comment_date">2022.11.23 00:05:55</div>
					</div>
				</li>
				<li class="comment_item">
					<div class="comment_reply_symbol" style="margin-left: 0px;">
						└</div>
					<div class="comment_area">
						<div class="nickname">원숭이</div>
						<div class="comment_txt">코뿔소님 안녕하세요</div>
						<div class="comment_date">2022.11.23 00:06:55</div>
					</div>
				</li>
				<li class="comment_item">
					<div class="comment_reply_symbol" style="margin-left: 21px;">
						└</div>
					<div class="comment_area">
						<div class="nickname">바보</div>
						<div class="comment_txt">원숭이님 안녕하세요</div>
						<div class="comment_date">2022.11.23 10:05:55</div>
					</div>
				</li>
				<li class="comment_item">
					<div class="comment_area">
						<div class="nickname">자동차</div>
						<div class="comment_txt">안녕하세요</div>
						<div class="comment_date">2022.11.23 15:05:55</div>
					</div>
				</li>
			</ul>
			<div class="comment_writer">
				<div class="nickname">화난소</div>
				<textarea class="comment_input_area" placeholder="댓글을 입력하세요"
					onkeydown="resize(this)" onkeyup="resize(this)"></textarea>
				<div class="comment_btn_box">
					<button class="comment_btn">댓글 입력</button>
				</div>

			</div>
		</div>
	</div>
	<script src="js/resize.js"></script>
	<script src="test.js"></script>
</body>
</html>