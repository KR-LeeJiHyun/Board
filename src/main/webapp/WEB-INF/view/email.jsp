<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/email.css">
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
                <div class="form_wrap">
                    <form id="info_form" onsubmit="return false;" method="post">
                        <div class="info_row">
                            <div class="tag">비밀번호</div>
                            <div class="pw_row">
                                <input type="password" id="pw" placeholder="비밀번호" required>
                            </div>
                        </div>
                        <div class="info_row">
                            <div class="tag">이메일</div>
                            <div class="email_row">
                                <input id="email" type="text" placeholder="이메일 입력" required>
                                <select id="address" class="email_adress">
                                    <option name="address" value="">직접입력</option>
                                    <option name="address" value="@google.com">@google.com</option>
                                    <option name="address" value="@naver.com">@naver.com</option>
                                    <option name="address" value="@nate.com">@nate.com</option>
                                </select>
                            </div>
                            <div class="err_msg_wrap">
                                <span id="email_err_msg" class="hidden">*이메일 주소가 아닙니다.</span>
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
        <script src="/community/js/email.js"></script>
    </body>
</html>