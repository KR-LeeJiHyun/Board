<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/regist.css">
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
                <div class="regist_wrap">
                    <form id="regist_form" onsubmit="return false;" method="post">
                        <div class="tag">이름</div>
                        <div class="input_row">
                            <input type="text" id="name" placeholder="이름 입력" required>
                        </div>
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
                        <div class="tag">아이디</div>
                        <div class="id_row">
                            <input id="id" type="text" id="id" placeholder="아이디 입력" required>
                            <button id="duplicate_id_button" type="button" class="small_button">중복확인</button>
                            <input id="chk_id" type="text" class="hidden" required readonly>
                        </div>
                        <div class="err_msg_wrap">
                            <span id="invalid_id_err_msg" class="hidden">*사용할 수 없는 아이디입니다.</span>
                            <span id="duplicate_id_err_msg" class="hidden">*중복된 아이디입니다.</span>
                        </div>
                        <div class="tag">비밀번호</div>
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

                        <div class="tag">비밀번호 재확인</div>
                        <div class="pw_row">
                            <input type="password" id="confirmation_pw" placeholder="비밀번호 재확인" required>
                            <i id="confirmation_eye" class="fa-solid fa-eye gray_eye"></i>
                        </div>
                        <div class="err_msg_wrap">
                            <span id="confirmation_pw_err_msg" class="hidden">*비밀번호가 일치하지 않습니다.</span>
                        </div>

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
                        <div class="tag">생년월일</div>
                        <div class="select_row">
                            <select class="year">
                                <option name="year">년도</option>
                                <option name="year" value="1995">95</option>
                            </select>
                            <select class="month">
                                <option name="month">월</option>
                                <option name="month" value="1">1</option>
                            </select>
                            <select class="day">
                                <option name="day">일</option>
                                <option name="day" value="1">1</option>
                            </select>
                        </div>
                        <div class="button_wrap">
                            <button id="back_button" type="button" class="medium_button_white">뒤로가기</button>
                            <input id="submit_button" class="medium_button" type="submit" value="가입하기">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="/community/js/jquery.js"></script>
        <script src="/community/js/regist.js"></script>
    </body>
</html>