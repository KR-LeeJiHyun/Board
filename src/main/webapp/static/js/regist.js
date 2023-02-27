// 비밀번호 적합성 검사
function checkPw() {
    const numberPattern = /[0-9]/; //숫자
    const alphabetPattern = /[a-zA-Z]/; //영어
    // var specialPattern = /[~!@#\#$%<>^&*]/; //특수문자
    const specialPattern = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/; //특수문자

    const pw = document.querySelector("#pw");
    const pwValue = pw.value;
    const pwLen = pwValue.length;
    const pwErrMsg = document.querySelector("#pw_err_msg");

    let result = false;

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
    const address = document.querySelector("#address");
    const emailaddress = email.value + address.value;
    const emailErrMsg =  document.querySelector("#email_err_msg");
    const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; //이메일

    if(emailPattern.test(emailaddress)){
        emailErrMsg.classList.add("hidden");
    }else {
        emailErrMsg.classList.remove("hidden");
    }
}

function checkId() {
    const pattern = /^[a-zA-Z0-9-_]{5,20}$/;
    const id = document.getElementById('id').value;
    const idErrMsg = document.getElementById('invalid_id_err_msg');
    if (pattern.test(id)) {
        idErrMsg.classList.add("hidden");
    } else {
        idErrMsg.classList.remove("hidden");
    }
}

function checkNickname() {
    const pattern = /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣-_]{5,20}$/;
    const nickname = document.getElementById('nickname').value;
    const nicknameErrMsg = document.getElementById('invalid_nickname_err_msg');
    if (pattern.test(nickname)) {
        nicknameErrMsg.classList.add("hidden");
    } else {
        nicknameErrMsg.classList.remove("hidden");
    }
}

function init() {
    const pw = document.querySelector("#pw");
    const confirmationPw = document.querySelector("#confirmation_pw");
    const email = document.querySelector("#email");
    const address = document.querySelector("#address");
    
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

    address.addEventListener('change', (event) => {
        // 이메일 적합성
        checkEmail(); 
    });

    const idInput = document.getElementById("id");
    idInput.addEventListener('focusout', (event) => {
        // 기존 아이디랑 같지 않을 경우에 처리하기        
        checkId();
    });

    const duplicateIdButton = document.getElementById("duplicate_id_button");
    duplicateIdButton.addEventListener('click', (event) => {
        const id = document.getElementById("id").value;
        const chkId = document.getElementById('chk_id').value;
        const idErrMsg = document.getElementById('invalid_id_err_msg');
        if (id.length < 5 && !idErrMsg.classList.contains('hidden') && id == chkId) {
            return;
        }

        // 서버로 아이디 중복검사 보내기 ajax사용     
        const curURL = $(window.location)[0].href;
        const slashLastIndex = curURL.lastIndexOf("/"); 
        let url = curURL.substring(0, slashLastIndex + 1) + "id";        
        const data = {"id" : id};
        $.ajax({
            url: url,
            type: "GET",
            data : data,
            success: function(data) {
                $("#chk_id").attr("value", "");
                $("#chk_id").attr("value", id);
            },
            error: function(xhr) {
                $("#chk_id").attr("value", "");
            }
        });
    });

    const nicknameInput = document.getElementById("nickname");
    nicknameInput.addEventListener('focusout', (event) => {
        checkNickname();
    });

    const duplicateNicknameButton = document.getElementById("duplicate_nickname_button");
    duplicateNicknameButton.addEventListener('click', (event) => {
        const nickname = document.getElementById("nickname").value;
        const chkNickname = document.getElementById('chk_nickname').value;

        if (nickname.length < 5 && !nicknameErrMsg.classList.contains('hidden') && nickname == chkNickname) {
            return;
        }

        const nicknameErrMsg = document.getElementById('invalid_nickname_err_msg');
        if (nickname.length <= 0 && !nicknameErrMsg.classList.contains('hidden')) {
            return;
        }

        if (nickname == chkNickname) {
            return;
        }

        // 서버로 닉네임 중복검사 보내기 ajax사용     
        const curURL = $(window.location)[0].href;
        const slashLastIndex = curURL.lastIndexOf("/"); 
        let url = curURL.substring(0, slashLastIndex + 1) + "nickname";        
        const data = {"nickname" : nickname};

        $.ajax({
            url: url,
            type: "GET",
            data : data,
            success: function(data) {
                $("#chk_nickname").attr("value", "");
                $("#chk_nickname").attr("value", nickname);
            },
            error: function(xhr) {
                $("#chk_nickname").attr("value", "");
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

    const submitButton = document.getElementById("submit_button");
    submitButton.addEventListener('click', (event) => {
        const name = $("#name").val();
        const nickname = $("#nickname").val();
        const id = $("#id").val();
        const password = $("#pw").val();
        const confirmationPw = $("#confirmation_pw").val();
        const email = $("#email").val() + $("#address option:selected").val();
        const year = $(".year option:selected").val();
        const month = $(".month option:selected").val();
        const day = $(".day option:selected").val();
        const date = new Date(year, month, day);

        const data = {
            'name' : name,
            'nickname' : nickname,
            'id' : id,
            'password' : password,
            'confirmationPassword': confirmationPw,
            'email' : email,
            'birthday' : date
        };

        const curURL = $(window.location)[0].href;
        const slashLastIndex = curURL.lastIndexOf("/"); 
        const url = curURL.substring(0, slashLastIndex);

        $.ajax({
            url: url,
            type: "POST",
            data : data,
            success: function(data) {
                alert(data);
                document.location.href = "/community";
            },
            error: function(xhr) {
                alert(xhr.responseText);
                document.location.href = "/community/members/regist";
            }
        });
    });
}

init();