package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO JSP로치면 Data access Object
// bean 으로 등록이 된다는건 스프링 Ioc에서 객체를 가지고있는지를 물어보는 것 그래야지 DI가되는것이니까 이것은 자동으로 bean등록이 된다.
// @Repository 생략가능하다
public interface UserRepository extends JpaRepository<User,Integer> {
    //해당 JpaRepository는 User table이 관리하는것이고, 해당 테이블의 PK는 integer라는것
}
