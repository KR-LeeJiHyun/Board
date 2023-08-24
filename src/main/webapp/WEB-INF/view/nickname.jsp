<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/nickname.css">
        <!-- 눈모양 이모티콘-->
        <script src="https://kit.fontawesome.com/9182b3b25b.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <div class="header_inner">
                <span>
                    닉네임 변경
                </span> 
            </div>
        </header>

        <div class="container">
            <div class="content">
                <div class="regist_wrap">
                    <form id="regist_form" onsubmit="return false;" method="post">
                        <div class="info_row">
                            <div class="tag">비밀번호</div>
                            <div class="pw_row">
                                <input type="password" id="pw" placeholder="비밀번호" required>
                            </div>
                        </div>
                        <div class="info_row">
                            <div class="tag">닉네임</div>
                            <div class="nickname_row">
                                <input id="nickname" type="text" id="nickname" placeholder="닉네임 입력" required>                            
                                <button id="duplicate_nickname_button" type="button" class="small_button">중복확인</button>
                                <input id="chk_nickname" type="text" class="hidden" required readonly>
                            </div>
                            <div class="err_msg_wrap">
                                <span id="invalid_nickname_err_msg" class="hidden">*사용할 수 없는 닉네임입니다.</span>
                                <span id="duplicate_nickname_err_msg" class="hidden">*중복된 닉네임입니다.</span>
                            </div>                            
                        </div>
                        <div class="button_wrap">
                            <button id="back_button" type="button" class="medium_button_white">뒤로가기</button>
                            <input id="submit_button" class="medium_button" type="submit" value="변경">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="/community/js/jquery.js"></script>
        <script src="/community/js/nickname.js"></script>
    </body>
</html>