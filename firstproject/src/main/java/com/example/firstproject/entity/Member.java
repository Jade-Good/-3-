package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity // 엔티티 선언
@AllArgsConstructor
@ToString
public class Member {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue // 자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

    public Member() {

    }
}
