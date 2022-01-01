let index = {

    init: function(){
        $("#btn-save").on("click", ()=>{
            //function(){} 대신 ()=>{} 사용하는이유, this를 바인딩하기 위해서!!
            //function 사용하면 this가 window 객체 가리킴
            this.save();
        });
    },

    save: function(){
        //alert('user의 save함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        // console.log(data);

        // ajax호출시 default가  비동기 호출

        $.ajax({
            type:"POST",
            url:"/blog/api/user", //join을 안붙혀도 post면 insert라는 것을 안다.
            dataType : "json",
            //data: JSON.stringify(data), // http body 데이터 보낼 때는 MIME 필요
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType:"json" // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
            // 요청을 서버로해서 응답이 왔을 때, 결과가 기본적으로 모든 것이 String(문자열). 생긴게 json이라면 => javascript 오브젝트로 변경해줌
        }).done(function(resp){

            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            //location.href="/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));

        });


        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    }



}

index.init(); // 호출하지않으면 아무일도 일어나지 않음