package com.cos.blog.handler;


import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디에서든 Exception이 발생하면 해당 Class로 들어옴
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    // IllegalArgumentException이 발생하면 그 함수에대한 Exception은 여기서처리 e에 전달
    public ResponseDto<String> handleArgumentException(Exception e) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),  e.getMessage());
    }

    /*
    @ExceptionHandler(value = Exception.class)
    // 모든Exception이 발생하면 그 함수에대한 Exception은 여기서처리 e에 전달
    public String handleArgumentException(Exception e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }*/


}

