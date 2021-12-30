package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//html파일이 아니라 data를 리턴해주는 controller -> RestController
@RestController
public class DummyControllerTest {

    @Autowired //의존성주입(DI)
    private UserRepository userRepository;
    // userRepository 그냥 null 언제 null? - 스프링이 RestController읽어서 DummyControllerTest띄워줄 때 null
    // 그때, @Autowired 붙히면 DummyControllerTest메모리에뜰 때 UserRepository 같이 뜬다.


    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) { //최고 부모인 Exception 걸어도됨
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id : "+id;
    }


    @Transactional //save()호출 없이 db에 저장됨, --> "더티체킹" , 함수종료시 자동 commit됨
    //email,password 받아야함 수정가능한 것만!
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
        //json data받으려면 @RequestBody 필요
        // json 데이터를 요청 -> spring의 MessageConverter의 Jackson라이브러리가 ava object로 변환해서 받아줌
        System.out.println("id : " +id);
        System.out.println("password : " +requestUser.getPassword());
        System.out.println("email : " +requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);
        // save(requestUser) 하기에는 null값이 너무많음
        // save는 insert할 때 사용됨 , update할 때는 save를 쓰지않음, 받지않은 값은 null로 update가 되기 때문
        // 그래서 받은 id로 찾은 user를 가져와서 save(user)하면 null값은 채워져있기 때문에 새로운값만 update
        // save 함수는 id를 전달하지않으면 insert를 해주고, 전달하면 update , id전달 하고 해당데이터 없으면 insert
        return user;
    }

    // http://localhost:8000/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }


    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    //http://localhost:8000/blog/dummy/user/page=1 -> 2번째 page보는방법 ,0부터시작
    @GetMapping("/dummy/user")
    public List<User> PageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser =userRepository.findAll(pageable);

        List<User> users= pagingUser.getContent();
        return users;
    }

    /* public Page<User> PageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> users =userRepository.findAll(pageable);
        return users;
    }
    Page 모든것 다 return ,
    content만 return 받고싶으면 List<User> users =userRepository.findAll(pageable).getContent();
    return users;*/


    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //user/4를 찾으면 내가 db에서 못찾으면 user가 null이 되니까 return시 null이 된다면
        //프로그램에 문제가 되니, Optional로 User객체를 감싸서 가져오면 null인지 아닌지 판단해서 return


        /*람다식
        User user =userRepository.findById(id).orElseThrow(()->{

            return new IllegalArgumentException("해당 사용자는 없습니다.");

        });*/

        User user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            //.orElseGet은 없으면 객체 만들어서 user에 넣으라는 것, 안에 넣을 수 있는 파라미터는 supplier type new!
            //IlligalArgumentException -> 잘못된 Argument가 들어왔다는 것, 존재하지않는.

            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        return user;
        /* user 객체 = 자바 object
         요청 : 웹브라우저 -> 웹브라우저는 자바가 들고있는 object를 이해를 못함, 그래서 변환을해야함
         웹브라우저가 이해할 수 있는 데이터로! 가장 좋은것은 json!
         변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브 러리)
         Springboot는 MessageConverter라는 애가 응답시에 자동 작동
         만약에 자바 오브젝트를 return하게되면 MessageConverter가 jackson이라는 lib를 호출해서
         user object를 json으로 변환해서 브라우저에 던져줍니다. */


    }


    //http://localhost:8000/blog/dummy/join (요청)
    @PostMapping("/dummy/join")
    public String join(User user) {
        //join(String username, String password, String email) { 변수명을 적는다면, key=value형태의 데이터를 받는다는것
        // @RequestParam("username") 생략하고 변수명 정확하게 적으면
        // http의 body에 username,password, email 데이터를 가지고 요청하게되면 그 세개의 값이 위의 변수명에 쏙 들어감
        System.out.println("id : " + user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());
        System.out.println("role : " + user.getRole());
        // Role같은 경우는 Column default값으로 user 되어있으면 role값은 없어야함 그래야지null로 안들어가고 user들어감  class위에 @DynamicInsert

        user.setRole(RoleType.USER); // 들어갈타입을 ""로 쓸 수없음, RoleType에서 정해둔 것만
        userRepository.save(user);
        return "회원가입이 완료 되었습니다.";
    }


}
