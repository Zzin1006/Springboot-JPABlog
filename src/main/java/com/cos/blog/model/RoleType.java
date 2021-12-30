package com.cos.blog.model;

public enum RoleType {
    USER,ADMIN

    // enum을 만들면 내가 넣을 수 있는 값을 강제할 수 있다. USER아니면 ADMIN만!
    // enum은 데이터의 도메인을 만들 때 사용됨, 도메인이라는 것은 어떠한 범위를 말함
    // ex) 남,여만 넣는다면 남자,여자 이런식으로 잘못넣지 않도록 데이터값을 강제시킬 수 있는 장점이있다.
}
