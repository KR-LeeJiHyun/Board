<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/myPage.css">
        <!-- 눈모양 이모티콘-->
        <script src="https://kit.fontawesome.com/9182b3b25b.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <div class="header_inner">
                <span>
                    회원정보
                </span> 
            </div>
        </header>

        <div class="container">
            <div class="content">
                <div class="profile">
                    <div class="tag">이름</div>
                    <div class="name_row row">
                        <div type="text" id="name">${profile.name}</div>
                    </div>
                    <div class="tag">닉네임</div>
                    <div class="nickname_row row">
                        <div id="nickname" type="text" >${profile.nickname}</div>                          
                        <button type="button" class="small_button" onclick="location.href='/community/members/mypage/nickname'">변경</button>
                    </div>
                    <div class="tag">아이디</div>
                    <div class="id_row row">
                        <div id="id" type="text">${profile.id}</div>
                    </div>
                    <div class="tag">이메일</div>
                    <div class="email_row row">
                        <div id="email" type="text">${profile.email}</div>
                        <button type="button" class="small_button" onclick="location.href='/community/members/mypage/email'">변경</button>
                    </div>
                    <div class="tag">비밀번호</div>
                    <div class="pw_row row">
                        <div id="password" type="text" >**************</div>    
                        <button type="button" class="small_button" onclick="location.href='/community/members/mypage/password'">변경</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>