function checkId() {

}

// 비밀번호 적합성 검사
function checkPw() {
    var numberPattern = /[0-9]/; //숫자
    var alphabetPattern = /[a-zA-Z]/; //영어
    var specialPattern = /[~!@#\#$%<>^&*]/; //특수문자

    const pw = document.querySelector("#pw");
    const pwValue = pw.value;

    //숫자 체크
    if (numberPattern.test(pwValue)) {
        document.querySelector("#number").classList.add("green_font");
    } else {
        document.querySelector("#number").classList.remove("green_font");
    }

    //영어 체크
    if(alphabetPattern.test(pwValue)){
        document.querySelector("#alphabet").classList.add("green_font");
    } else {
        document.querySelector("#alphabet").classList.remove("green_font");
    }

    if(specialPattern.test(pwValue)) {
        document.querySelector("#special").classList.add("green_font");
    } else {
        document.querySelector("#special").classList.remove("green_font");
    }
}

// 비밀번호 재확인
function confirmPw() {
    const pw = document.querySelector("#pw");
    const confirmationPw = document.querySelector("#confirmation_pw");
    const confirmationPwErrMsg = document.querySelector("#confirmation_pw_err_msg");

    if (pw.value === confirmationPw.value) {
        confirmationPwErrMsg.classList.add("hidden");
    } else {
        confirmationPwErrMsg.classList.remove("hidden");
    }
}

function init() {
    const pw = document.querySelector("#pw");
    const confirmationPw = document.querySelector("#confirmation_pw");
    pw.addEventListener('change', (event) => {
        // 비밀번호 적합성
        checkPw();
        // 비밀번호 재확인
        confirmPw();        
    });

    confirmationPw.addEventListener('change', (event) => {
        // 비밀번호 재확인
        confirmPw(); 
    });
}

init();