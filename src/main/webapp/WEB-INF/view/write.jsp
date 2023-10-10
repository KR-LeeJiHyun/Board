<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="content_banner">
			<c:if test="${category == 'free'}">
				<h1>자유게시판</h1>
			</c:if>
			<c:if test="${category == 'game'}">
				<h1>게임게시판</h1>
			</c:if>
			<c:if test="${category == 'sports'}">
				<h1>스포츠게시판</h1>
			</c:if>
			<c:if test="${category == 'pet'}">
				<h1>애완동물게시판</h1>
			</c:if>
			<c:if test="${category == 'it_finance'}">
				<h1>IT/금융게시판</h1>
			</c:if>
		</div>
		<form action="/community/board/new/${category}" method="post">
			<div class="post_header">
				<input name="title" class="post_title" placeholder="제목을 입력해 주세요." />
				<c:if test="${category == 'free'}">
					<select style="float: right;" required>
						<option name="subCategory" selected value="free">자유</option>
					</select>
				</c:if>
				<c:if test="${category == 'game'}">
					<select style="float: right;" required>
						<option name="subCategory" value="online">온라인</option>
						<option name="subCategory" value="mobile">모바일</option>
						<option name="subCategory" value="console">콘솔</option>
					</select>
				</c:if>
				<c:if test="${category == 'sports'}">
					<select style="float: right;" required>
						<option name="subCategory" value="soccer">축구</option>
						<option name="subCategory" value="baseball">야구</option>
						<option name="subCategory" value="basketball">농구</option>
						<option name="subCategory" value="etc">기타</option>
					</select>
				</c:if>
				<c:if test="${category == 'pet'}">
					<select style="float: right;" required>
						<option name="subCategory" value="dog">강아지</option>
						<option name="subCategory" value="cat">고양이</option>
						<option name="subCategory" value="etc">기타</option>
					</select>
				</c:if>
				<c:if test="${category == 'it_finance'}">
					<select style="float: right;" required>
						<option name="subCategory" value="it">IT</option>
						<option name="subCategory" value="finance">금융</option>
					</select>
				</c:if>
			</div>
			<textarea name="content" class="post_content"
				placeholder="내용을 입력해 주세요."></textarea>
			<input class="write_btn" type="button" value="취소"></input> <input
				class="write_btn" type="submit" value="제출"></input>
		</form>
	</div>
</body>
</html>