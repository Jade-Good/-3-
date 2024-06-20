package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/members/" + saved.getId();
    }


    @GetMapping("/members")
    public String index(Model model) {
        List<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);

        return "members/index";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);

        return "members/show";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String edit(MemberForm form) {
        Member memberEntity = form.toEntity();

        memberRepository.findById(memberEntity.getId()).ifPresent(target -> memberRepository.save(memberEntity));

        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("사용자 정보 삭제 요청 들어옴!!");
        memberRepository.findById(id).ifPresent(memberEntity->{
            memberRepository.delete(memberEntity);
            redirectAttributes.addFlashAttribute("msg", memberEntity.getEmail()+"의 정보가 삭제되었습니다.");
        });
//        Member memberEntity = memberRepository.findById(id).orElse(null);
//        if (memberEntity != null) {
//            memberRepository.delete(memberEntity);
//            redirectAttributes.addFlashAttribute("msg", memberEntity.getEmail()+"의 정보가 삭제되었습니다.");
//        }
        return "redirect:/members";
    }
}
