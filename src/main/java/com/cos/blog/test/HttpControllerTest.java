package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답을 해주는 것이 controller 어떠한 응답? data를 응답!
//만약 데이터를 응답해주는 것이 아닌 html파일을 응답해주는 controller를 만들고싶다면 @Controller

@RestController
public class HttpControllerTest {

    private static final String TAG= "HttpControllerTest : ";

    //application.yml 에 server 설정 8000/blog로 contextpath진입점 설정 -> localhost:8000/blog/http/lombok
    @GetMapping("/http/lombok")
    public String LombokTest() {


        //Member m1 = new Member(1,"ssar","1234","ssar@nate.com"); lombok AllArgsConstructor 동작
        //Member m2 = new Member(); lombok NoArgsConstructor 동작

        Member m1 = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        // @builder 사용해서 필요한것만,순서 안지켜도됨

        System.out.println(TAG+"getter : "+m1.getUsername());
        m1.setUsername("cos");
        System.out.println(TAG+"setter : "+m1.getUsername());


        return "lombok test 완료";
    }






    //인터넷 브라우저 요청은 무조건 get요청밖에 할 수가 없다. -> 그래서 다른 메서드요청시 postman사용!
    //http://localhost:8080/http/get (selete)
    @GetMapping("/http/get")
    // return하는 데이터는 문자열
    public String getTest(Member m) { //?id=2&username=ssar&password=1234&email=ssar@nate.com 스프링에서는 멤버에 집어넣어주는 역할도 함
        //@RequestParam int id,String username 한개씩 받는방법 -> +id , +username // Memberobject를적으면 한번에 알아서
        return "get 요청:"+m.getId()+"," +m.getUsername()+","+m.getPassword()+","+m.getEmail();
    }

    //http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) { // MIME 타입이 text/json, application json으로보낼 때 Member object로 받을 수 있음 mapping (MessageConverter스프링부트가함)
        //post는 데이터를 주소에붙히지않고 body에 담아보냄 - raw --> null text받을 때 RequestBody로 받아야함 String text // text/plain
        return "post 요청:"+m.getId()+"," +m.getUsername()+","+m.getPassword()+","+m.getEmail();
        }

   /* //http://localhost:8080/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(Member m) { //post는 데이터를 주소에붙히지않고 body에 담아보냄
        return "post 요청:"+m.getId()+"," +m.getUsername()+","+m.getPassword()+","+m.getEmail();
        }*/

    //http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청:"+m.getId()+"," +m.getUsername()+","+m.getPassword()+","+m.getEmail();
        }

    //http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
            return "delete 요청";
        }
}
