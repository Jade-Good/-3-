package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member/new")
    public String newMemberForm() {
        return "members/new";
    }
    @PostMapping("/member/join")
    public String regist(MemberForm form) {
        log.info(form.toString());
//        System.out.println(form.toString());

        // 1. DTO를 엔티티로 변환
        Member member = form.toEntity();
        log.info(member.toString());
//        System.out.println(member.toString());

        // 2. 레포지토리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "";
    }
}
