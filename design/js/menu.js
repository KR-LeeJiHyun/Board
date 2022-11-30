var mouseState = false;

function slide(categoryDetail) {
    const list = categoryDetail.querySelectorAll("div");

    const height = list[0].offsetHeight;
    const totalHeight = height * list.length;
    
    const categoryListBox = document.querySelector(".category_list_box");
    categoryListBox.style.height = (72 + totalHeight) + "px";
    
}

function slideUp() {
    if (!mouseState) {
        const categoryListBox = document.querySelector(".category_list_box");
        categoryListBox.style.height = "72px";
    }    
}

function addEventDetail() {
    // const list = document.querySelectorAll(".category_list > li");
    // for (let idx = 0; idx < list.length; ++idx) {
    //     list[idx].addEventListener("mouseover", function() {
    //         mouseState = true;

    //         slide(list[idx].querySelector(".category_detail"));
    //     })        
    // }

    const boxes = document.querySelectorAll(".category_box");
    for (let idx = 0; idx < boxes.length; ++idx) {
        boxes[idx].addEventListener("mouseover", function() {
            mouseState = true;

            const detail = boxes[idx].parentNode.querySelector(".category_detail");
            slide(detail);
        })        
    }

    const categoryListBox = document.querySelector(".category_list_box");
    categoryListBox.addEventListener("mouseout", function() {
        mouseState = false;
        setTimeout(slideUp, 1000);
    })
}


function init() {
    addEventDetail();    
}

init();