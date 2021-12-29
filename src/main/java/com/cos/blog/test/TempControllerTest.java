package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // data가아닌 file return
public class TempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome() {
        //파일 리턴 기본 경로 : src/main/resources/static 
        //리턴명 :  /home.html
        //풀경로: src/main/resources/static/home.html --> "/"주의
        // static 폴더에는 브라우저가 인식할 수 있는 정적파일만 올 수 있다. ex) html, png.. // JSP X
        return "/home.html";
    }

    @GetMapping("temp/img")
    public String tempImg() {
        return "/a.jpg";
    }


    @GetMapping("temp/jsp")
    public String tempJsp() {
        //prefix: /WEB-INF/views/
        // suffix: .jsp
        // 풀네임 : /WEB-INF/views/test.jsp --> "/"와 확장자jsp 주의
        return "test";
    }


}
