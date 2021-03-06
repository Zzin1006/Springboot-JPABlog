package com.cos.blog.service;


import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌. IoC를 해준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // 전체가 성공을하면 commit , 하나라도 실패하면 rollback
    public void 회원가입(User user) {
        userRepository.save(user);
    }
}
