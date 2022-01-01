package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //클래스를 table화 User 클래스가 MySQL에 테이블이 생성된다.
// @DynamicInsert insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다는 것 만약 oracle 사용시 시퀀스, mySQL시 auto_increment사용한다는 것

    private int id; //시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username; //아이디

    @Column(nullable = false, length = 100) // 123456 => 해쉬로변경해서 비밀번호 암호화 넉넉하게 공간줌
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    @Enumerated(EnumType.STRING) // DB는 RoleType이라는 것이없음 해당 enum이 string 이라는 것을 알려줘야함
    private RoleType role; 
    // Enum 쓰는게 좋다. 오타방지! (managerrrr) 또한 Enum을쓰면 data의 도메인을 만들 수 있다. ADMIN, USER, MANAGER 권한
    // Type을 RoleType이라고하면 명시한것중에서만 사용할 수 있음

    @CreationTimestamp //시간이 자동으로 입력이됨
    private Timestamp createDate; // javasql이 들고있는 timestamp가있음

    




}
