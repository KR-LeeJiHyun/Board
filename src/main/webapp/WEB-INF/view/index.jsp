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
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="content_banner">
			<h1>자유게시판</h1>
			<form class="search_form">
				<select name = "field">
				
					<option 
					<c:if test="${postSearch.field == 'title'}">
						selected
					</c:if> 
					value="title">제목</option>
					<option 
					<c:if test="${postSearch.field == 'writer'}">
						selected
					</c:if> 
					value="writer">글쓴이</option>
				</select>
				 <input id="search_content" name="query" type="text" value="${postSearch.query}">
				<button class="search_button">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
				<select name= "order" class="order">
					<option
					<c:if test="${postSearch.order == 'regdate'}">
						selected
					</c:if> 
					value="regdate">날짜 순</option>
					<option
					<c:if test="${postSearch.order == 'hit'}">
						selected
					</c:if> 
					value="hit">조회수 순</option>
				</select> 
			</form>
			<div class="order_edit_wrap">
				<a href="/community/board/new/${category}"> <i class="fa-solid fa-pencil"></i></a>
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

				<c:forEach var = "post" items = "${postList}">
					<tr>
						<td>${post.postId}</td>
						<td><a href="/community/board/${category}/${post.category}/${post.postId}"><c:out value="${post.title}" escapeXml="true" /></a><span
							class="ballon">${commentCntMap[post.postId]}</span></td>
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
								<a href="?page=${st.index}&field=${postSearch.field}&query=${postSearch.query}&order=${postSearch.order}">
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