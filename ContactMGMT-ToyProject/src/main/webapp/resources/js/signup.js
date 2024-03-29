

const checkObj = {
    "inputEmail" : false, // 이메일
    "inputPw" : false, // 비밀번호
    "inputPw2" : false, // 비번 확인
    "inputName" : false // 이름
};

// 이메일 중복 검사 구현 실패..유효성만
const inputEmail = document.getElementById("inputEmail");
const idMsg = document.getElementById("idMsg");

const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;

function validateEmail() {
   
   
    if(pattern.test(inputEmail.value)) {

        inputEmail.style.background = "yellowgreen";
        inputEmail.style.color = "white";
        idMsg.innerText = "사용 가능한 형식입니다."
        idMsg.classList.add("confirm");
        idMsg.classList.remove("error");
        checkObj.inputEmail = true;

    } else {
        inputEmail.style.background = "red";
        inputEmail.style.color = "white";
        idMsg.innerText = "사용 불가한 형식입니다."
        idMsg.classList.add("error");
        idMsg.classList.remove("confirm");
        checkObj.inputEmail = false;
    }

}

inputEmail.addEventListener("focus", validateEmail);
inputEmail.addEventListener("blur", validateEmail);
inputEmail.addEventListener("keyup", validateEmail);






// 비밀번호 일치 여부
const inputPw = document.getElementById("inputPw");
const inputPw2 = document.getElementById("inputPw2");

inputPw.addEventListener("keyup", function() {
    

    if(inputPw.value == inputPw2.value && inputPw.value.length != 0) {
        pw2Msg.innerText = "비밀번호가 일치합니다.";
        pw2Msg.classList.add("confirm");
        pw2Msg.classList.remove("error");
        checkObj.inputPw = true;        
        checkObj.inputPw2 = true;
        
    } else {
        pw2Msg.innerText = "비밀번호가 일치하지 않습니다.";
        pw2Msg.classList.add("error");
        pw2Msg.classList.remove("confirm");
        checkObj.inputPw = false;        
        checkObj.inputPw2 = false;
    }

});



inputPw2.addEventListener("keyup", function() {
    
    if(inputPw.value == inputPw2.value && inputPw.value.length != 0) {
        pw2Msg.innerText = "비밀번호가 일치합니다.";
        pw2Msg.classList.add("confirm");
        pw2Msg.classList.remove("error");
        checkObj.inputPw = true;        
        checkObj.inputPw2 = true;
        
    } else {
        pw2Msg.innerText = "비밀번호가 일치하지 않습니다.";
        pw2Msg.classList.add("error");
        pw2Msg.classList.remove("confirm");
        checkObj.inputPw = false;        
        checkObj.inputPw2 = false;
    }

});



// 이름 유효성
const inputName = document.getElementById("inputName");

inputName.addEventListener("change", function() {
    const regExp = /^[가-힣]{2,5}$/;

      if(regExp.test(this.value)) {
        nameMsg.innerText = "정상 입력";
        nameMsg.classList.add("confirm");
        nameMsg.classList.remove("error");
        checkObj.inputName = true;
    } else {
        nameMsg.innerText = "2글자 ~ 5글자 사이 한글만 입력하세요!";
        nameMsg.classList.add("error");
        nameMsg.classList.remove("confirm");
        checkObj.inputName = false;
    }
});


function signCheck() {
    
    for(let key in checkObj) {
        if (!checkObj[key]) {
            alert("입력한 내용이 올바르지 않습니다. 다시 확인해주세요!");
            return false;
        }
    }
    return true;
};



// 즐겨찾기

function changeStar(e) {

    if(confirm("즐겨찾기를 변경하시겠습니까?")) {
        let currentSymbol = e.innerHTML; 
        
        if (currentSymbol == '☆') {
            e.innerHTML = '★'; 
        } else {
            e.innerHTML = '☆'; 
        }
    }
    
    return false; // 기본 이벤트 동작을 중단
}