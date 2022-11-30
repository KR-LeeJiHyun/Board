function check(event) {
    console.log("hello");
    event.preventDefault()    
}

function init() {
    const btnCheckList = document.querySelectorAll(".btn_check");

    for (let idx = 0; idx < btnCheckList.length; ++idx) {
        const btnCheck = btnCheckList[idx];
        btnCheck.addEventListener("submit", check);
    }
}

init();