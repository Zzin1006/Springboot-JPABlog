package com.cos.blog.handler;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디에서든 Exception이 발생하면 해당 Class로 들어옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    // IllegalArgumentException이 발생하면 그 함수에대한 Exception은 여기서처리 e에 전달
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }

    /*
    @ExceptionHandler(value = Exception.class)
    // 모든Exception이 발생하면 그 함수에대한 Exception은 여기서처리 e에 전달
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }*/


}

