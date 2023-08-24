<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/password.css">
        <!-- 눈모양 이모티콘-->
        <script src="https://kit.fontawesome.com/9182b3b25b.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <header>
            <div class="header_inner">
                <span>
                    비밀번호 변경
                </span> 
            </div>
        </header>

        <div class="container">
            <div class="content">
                <div class="password_wrap">
                    <form id="password_form" onsubmit="return false;" method="post">
                        <div class="info_row">
                            <div class="info_row">
                                <div class="tag">현재 비밀번호</div>
                                <div class="pw_row margin">
                                    <input type="password" id="current_pw" placeholder="현재 비밀번호 입력" required>
                                </div>
                            </div>
                            <div class="info_row">
                                <div class="tag">새 비밀번호</div>
                                <div class="pw_row">
                                    <input type="password" id="pw" placeholder="비밀번호 입력(문자, 숫자, 특수문자 포함 8~20자)" required>
                                    <i id="pw_eye" class="fa-solid fa-eye gray_eye"></i>
                                </div>
                                <div class="pw_err_msg_wrap">
                                    <span id="pw_err_msg" class="hidden">*20자 이내의 비밀번호를 입력해주세요.</span>
                                </div>
                                <div class="chk_box">
                                    <ul>
                                        <li id="alphabet">영문자</li>
                                        <li id="number">숫자</li>
                                        <li id="special">특수문자</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="info_row">
                                <div class="tag">새 비밀번호 확인</div>
                                <div class="pw_row">
                                    <input type="password" id="confirmation_pw" placeholder="비밀번호 확인" required>
                                    <i id="confirmation_eye" class="fa-solid fa-eye gray_eye"></i>
                                </div>
                                <div class="err_msg_wrap">
                                    <span id="confirmation_pw_err_msg" class="hidden">*비밀번호가 일치하지 않습니다.</span>
                                </div>
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
        <script src="/community/js/password.js"></script>
    </body>
</html>