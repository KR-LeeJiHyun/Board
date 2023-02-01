// 비밀번호 적합성 검사
function checkPw() {
    var numberPattern = /[0-9]/; //숫자
    var alphabetPattern = /[a-zA-Z]/; //영어
    var specialPattern = /[~!@#\#$%<>^&*]/; //특수문자

    const pw = document.querySelector("#pw");
    const pwValue = pw.value;
    const pwLen = pwValue.length;
    const pwErrMsg = document.querySelector("#pw_err_msg");

    var result = false;

    //숫자 체크
    if (numberPattern.test(pwValue)) {
        document.querySelector("#number").classList.add("green_font");
        result = true;
    } else {
        document.querySelector("#number").classList.remove("green_font");
        result = false;
    }

    //영어 체크
    if(alphabetPattern.test(pwValue)){
        document.querySelector("#alphabet").classList.add("green_font");
        result = true;
    } else {
        document.querySelector("#alphabet").classList.remove("green_font");
        result = false;
    }

    if(specialPattern.test(pwValue)) {
        document.querySelector("#special").classList.add("green_font");
        result = true;
    } else {
        document.querySelector("#special").classList.remove("green_font");
        result = false;
    }

    //길이 체크
    if(pwLen >= 8 && pwLen <= 20) {
        pwErrMsg.classList.add("hidden");
        result = true;
    } else {
        pwErrMsg.classList.remove("hidden");
        result = false;
    }

    return result;
}

// 비밀번호 재확인
function confirmPw() {
    const pw = document.querySelector("#pw");
    const confirmationPw = document.querySelector("#confirmation_pw");
    const confirmationPwErrMsg = document.querySelector("#confirmation_pw_err_msg");
    const confirmationEye = document.querySelector("#confirmation_eye");

    if (pw.value === confirmationPw.value) {
        confirmationPwErrMsg.classList.add("hidden");
        if(pw.value != null){
            confirmationEye.classList.remove("gray_eye");
            confirmationEye.classList.add("green_eye");
        }
    } else {
        confirmationPwErrMsg.classList.remove("hidden");
        confirmationEye.classList.remove("green_eye");
        confirmationEye.classList.add("gray_eye");
    }
}

function finalCheckPw() {
    const pwEye = document.querySelector("#pw_eye");
    if(checkPw()){
        pwEye.classList.remove("gray_eye");
        pwEye.classList.add("green_eye");
    }else {
        pwEye.classList.remove("green_eye");
        pwEye.classList.add("gray_eye");
    }
}

// 이메일 적합성
function checkEmail() {
    const email = document.querySelector("#email");
    const adress = document.querySelector("#adress");
    const emailAdress = email.value + adress.value;
    const emailErrMsg =  document.querySelector("#email_err_msg");
    var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; //이메일

    if(emailPattern.test(emailAdress)){
        emailErrMsg.classList.add("hidden");
    }else {
        emailErrMsg.classList.remove("hidden");
    }
}

function init() {
    const pw = document.querySelector("#pw");
    const confirmationPw = document.querySelector("#confirmation_pw");
    const email = document.querySelector("#email");
    const adress = document.querySelector("#adress");
    
    pw.addEventListener('change', (event) => {
        // 비밀번호 적합성
        checkPw();
        // 비밀번호 재확인
        confirmPw();
        //비밀번호 최종 확인        
        finalCheckPw();
    });

    confirmationPw.addEventListener('change', (event) => {
        // 비밀번호 재확인
        confirmPw();
    });

    email.addEventListener('change', (event) => {
        // 이메일 적합성
        checkEmail(); 
    });

    adress.addEventListener('change', (event) => {
        // 이메일 적합성
        checkEmail(); 
    });

    const duplicateIdButton = document.getElementById("duplicate_id_button");
    duplicateIdButton.addEventListener('click', (event) => {
        const id = document.getElementById("id").value;
        // 서버로 아이디 중복검사 보내기 ajax사용     
        const curURL = $(window.location)[0].href;
        const slashLastIndex = curURL.lastIndexOf("/"); 
        let url = curURL.substring(0, slashLastIndex + 1) + "id";        

        $.get(url, {"id" : id}, function(response) {
            $("#chk_id").attr("value", "");
            if (response == "none") {
                $("#chk_id").attr("value", "pass");
            }
        });
    });


    const duplicateNicknameButton = document.getElementById("duplicate_nickname_button");
    duplicateNicknameButton.addEventListener('click', (event) => {
        const nickname = document.getElementById("nickname").value;
        // 서버로 아이디 중복검사 보내기 ajax사용     
        const curURL = $(window.location)[0].href;
        const slashLastIndex = curURL.lastIndexOf("/"); 
        let url = curURL.substring(0, slashLastIndex + 1) + "nickname";        

        $.get(url, {"nickname" : nickname}, function(response) {
            $("#chk_nickname").attr("value", "");
            if (response == "none") {
                $("#chk_nickname").attr("value", "pass");
            }
        });
    });

    const backButton = document.getElementById("back_button");
    backButton.addEventListener('click', (event) => {
        /*
        // 뒤로갈 히스토리가 있는 경우
        if (document.referrer) {
            history.back();
        } else { // 히스토리가 없는 경우
            location.replace("login.html");
        }
        */
        location.replace("login.html");
    });
}

init();