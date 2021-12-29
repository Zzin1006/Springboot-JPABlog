package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL Autoincrement
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨. 그러면 글자용량이 굉장히커짐

    @ColumnDefault("0") //number값이니까 홑따옴표 필요x
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) // Many = Board , One = User 한명의 유저는 여러개의 게시글을 작성할 수 있다.
    // user는 한건밖에 없으니까 바로 join해서 가져온다는 것 , OneToMany는 기본전략이 FetchType이EAGER가 아님
    @JoinColumn(name="userId")// userId만적으면 연관관계가 없으니까 ManyToOne같이
    private User user; // ORM에서는 Key값으로 찾지않고 바로 UserObject로, DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) // fetch = FetchType.LAZY적지않아도 기본전략임 OneToMany , 댓글펼치기일 때 LAZY로 가져와도됨
    // mappedBy가 적혀있으면 연관관계의 주인이 아님, db에 컬럼을 만들지마세요!라는 것 난FK가 아님, 실제 FK는 Reply테이블의 board가 FK임.
    // One = Board , Many = Reply 하나의 보드에는 여러개의 답글
    // @JoinColumn(name="replyId") FK필요없어짐 왜냐? 답변은 여러개가 달릴 수 있으니까 하나의 Column은 하나의 값을 가져야함.
    private List<Reply> reply; // 한개의 댓글만 가져오면 안되니까 List에 담을 것 , reply는 board select시 값을 얻기위해 필요한 것
    
    @CreationTimestamp
    private Timestamp createDate;
}
