let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function(){} 이 아니라 ()=>{} 를 사용하는 이유 : this 를 바인딩하기 위해서
            this.save();
        });

        $("#btn-update").on("click", () => { // function(){} 이 아니라 ()=>{} 를 사용하는 이유 : this 를 바인딩하기 위해서
            this.update();
        });
    },
    // init: function() {
    //     let _this = this;
    //     $("#btn-save").on("click", function(){
    //         _this.save();
    //     });
    // },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password1").val(),
            email: $("#email").val()
        };

        // ajax 호출 시 default 가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경해 insert 요청
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/signup", // get url 과 post url 이 같아야 하나보다..??
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8",     //body 데이터가 어떤 타입인지(MIME)
            dataType: "json"    // 요청을 서버로해서   응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경
            // dataType 을 생략해도 알아서 파싱이 된다..
        }).done(function (response) {
            alert("회원가입이 완료되었습니다.");
            // alert("response: " + response);
            // console.log(response);
            location.href = "/";
        }).fail(function (error) {
            alert("회원가입에 실패하였습니다.");
            // console.log(error);
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password1").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "PUT",
            url: "/api/user/info/edit",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            alert("회원정보 수정이 완료되었습니다.");
            location.href = "/user/info";
        }).fail(function (error) {
            alert("회원정보 수정에 실패하였습니다.");
            alert(JSON.stringify(error));
        });
    },


}
//     login: function() {
//         alert('user의 save 함수 호출됨');
//         let data = {
//             username: $("#username").val(),
//             password: $("#password1").val(),
//         };
//
//         // ajax 호출 시 default 가 비동기 호출
//         // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경해 insert 요청
//         $.ajax({
//             // 회원가입 수행 요청
//             type: "POST",
//             url: "/api/user/login",
//             data: JSON.stringify(data), // http body 데이터
//             contentType: "application/json; charset=utf-8",     //body 데이터가 어떤 타입인지(MIME)
//             dataType: "json"    // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경
//             // dataType 을 생략해도 알아서 파싱이 된다..
//         }).done(function(response){
//             alert("로그인이 완료되었습니다.");
//             alert("response: " + response);
//             location.href="/";
//         }).fail(function(error){
//             alert(JSON.stringify(error));
//         });
//     }
// }

index.init();