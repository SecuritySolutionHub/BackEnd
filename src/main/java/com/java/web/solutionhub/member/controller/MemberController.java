package com.java.web.solutionhub.member.controller;


import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    @GetMapping("/members/joins")
    public String redirectJoinPage(){
        return "join";
    }

    @PostMapping("/members/join")
    public String joinMember(@RequestBody Member member) {
    	memberService.join(member);
        return "login";
    }

    @PostMapping("/members/login")
    public void loginMember( ) {

    }

}
