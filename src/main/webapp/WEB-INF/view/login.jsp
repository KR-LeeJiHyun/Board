<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <!-- style.css-->
        <link rel="stylesheet" href="/community/css/login.css">
    </head>    
    <body>
        <header>
            <div class="header_inner">
                <span>
                    LOGIN  
                    <img class="logo" src="/community/images/logo.jpg"/>   
                </span> 
            </div>
        </header>
        <div class="container">
            <div class="content">
                <div class="login_wrap">
                    <form id="login_form" method="POST">
                        <div class="id_pw_wrap">
                            <div class="input_row" id="id_line">
                                <input type="text" name="id" placeholder="아이디/이메일" required/>
                            </div>
                            <div class="input_row" id="pw_line">
                                <input type="password" name="password" placeholder="비밀번호" required/>
                            </div>
                        </div>
                        <div class="btn_wrap">
                            <button type="submit" class="btn_login">로그인</button>
                        </div>
                        <div class="login_keep_wrap">                            
                            <input type="checkbox" name="loginKeep" id="login_keep" />
                            <label for="login_keep"></label>
                            <span>자동 로그인</span>
                        </div> 
                    </form> 
                    <div class="find_wrap">
                        <a href="#" id="find_id_pw">아이디/비밀번호 찾기</a>
                        <a href="regist" id="regist">회원가입</a>
                    </div>  
                </div>
            </div>
        </div>
    </body>
    <c:if test="${error != null}">
        <script>
            alert("아이디 혹은 비밀번호가 잘못되었습니다.");
        </script>
    </c:if>
</html>