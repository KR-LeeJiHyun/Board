<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="css/regist.css">
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
                    <form id="regist_form">
                        <div class="tag">유저이름</div>
                        <div class="input_row">
                            <input type="text" placeholder="유저이름 입력" required>
                        </div>
                        <div class="tag">아이디</div>
                        <div class="id_row">
                            <input type="text" placeholder="아이디 입력" required>
                            <button id="duplicate_id_button" type="button" class="small_button">중복확인</button>
                        </div>
                        <div class="err_msg_wrap">
                            <span id="id_err_msg" class="hidden">*사용할 수 없는 아이디 입니다.</span>
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
                            <select id="adress" class="email_adress">
                                <option value="">직접입력</option>
                                <option value="@google.com">@ google.com</option>
                                <option value="@naver.com">@ naver.com</option>
                                <option value="@nate.com">@ nate.com</option>
                            </select>
                        </div>
                        <div class="err_msg_wrap">
                            <span id="email_err_msg" class="hidden">*이메일 주소가 아닙니다.</span>
                        </div>
                        <div class="tag">생년월일</div>
                        <div class="select_row">
                            <select class="year">
                                <option>년도</option>
                            </select>
                            <select class="month">
                                <option>월</option>
                            </select>
                            <select class="day">
                                <option>일</option>
                            </select>
                        </div>
                        <div class="button_wrap">
                            <button id="back_button" type="button" class="medium_button_white">뒤로가기</button>
                            <input class="medium_button" type="submit" value="가입하기">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/regist.js"></script>
    </body>
</html>