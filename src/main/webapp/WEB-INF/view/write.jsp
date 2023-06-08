<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세</title>
<link rel="stylesheet" href="css/write.css">

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
			<form>
			<header>
				<input class="post_header" placeholder="제목을 입력해 주세요."/>
			</header>
			<textarea class="post_content" placeholder="내용을 입력해 주세요."></textarea>
			<input class="write_btn" type="button" value="취소"></input>
			<input class="write_btn" type="submit" value="제출"></input>
		</form>		
	</div>
</body>
</html>