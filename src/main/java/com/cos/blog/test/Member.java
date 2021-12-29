package com.cos.blog.test;


import lombok.*;

/*
@Getter
@Setter --> 2개 다 만들고싶으면 @DATA
@RequiredArgsConstructor final 붙은 변수에 대한 Contructor를 만들어줌 */


@Data
@NoArgsConstructor // 빈생성자
public class Member {

    // 기본적으로 private로 하는 이유는 만약 변수의 값에 다이렉트로 접근해서 값을 수정해버리면 객체지향에 맞지않기때문
    // 이러한 상세값을 변경하기위해서 getter,setter를 만들어서 함수를 통해서 접근할 수 있도록
    private int id;
    private String username; //final 변경할일 없을 때
    private String password;
    private String email;

    //@AllArgsConstructor대신 Generate로 붙힌것
    @Builder // 사용하고싶은 필드만 순서상관없이 사용할 수 있도록 오버로딩안해도됨
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
