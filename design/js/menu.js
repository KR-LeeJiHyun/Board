var mouseState = false;

function slide(categoryDetail) {
    const list = categoryDetail.querySelectorAll("div");

    const height = list[0].offsetHeight;
    const totalHeight = height * list.length;
    categoryDetail.style.height = totalHeight;
    
    const categoryListBox = document.querySelector(".category_list_box");
    categoryListBox.style.height = (70 + totalHeight) + "px";
}

function slideUp() {
    if (!mouseState) {
        const categoryListBox = document.querySelector(".category_list_box");
        categoryListBox.style.height = 70 + "px";
    }    
}

function addEventDetail() {
    const list = document.querySelectorAll(".category_list > li");
    for (let idx = 0; idx < list.length; ++idx) {
        list[idx].addEventListener("mouseover", function() {
            mouseState = true;

            slide(list[idx].querySelector(".category_detail"));
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