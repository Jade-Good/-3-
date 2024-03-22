package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberForm {
    private String email; // 제목을 받을 필드
    private String password; // 내용을 받을 필드

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
