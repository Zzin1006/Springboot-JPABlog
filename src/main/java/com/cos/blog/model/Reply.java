package com.cos.blog.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment


    @Column(nullable = false, length = 200)
    private String content;
    
    //어느 게시글에있는 답글인지 연관관계가 필요함
    @ManyToOne // Many = Reply One = Board 하나의 게시글에는 여러개의 답글
    @JoinColumn(name="boardId")
    private Board board;

    //답변을 누가적었는지
    @ManyToOne // Many = Reply One = user, 여러개의 답글을 하나의 유저가 쓸 수 있다.
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

}
