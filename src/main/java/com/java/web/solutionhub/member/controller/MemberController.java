package com.java.web.solutionhub.member.controller;


import com.java.web.solutionhub.config.jwt.JwtTokenProvider;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.Data;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    
    
    // 회원 가입
    @PostMapping("/join")
    public Long join(@RequestBody CreateMemberRequest member) {
    	return memberService.join(MemberSaveRequsetDto.builder()
    			.userId(member.id)
    			.password(passwordEncoder.encode(member.password))
    			.companyEmail(member.companyEmail)
    			.roll(memberRoll.USER)
    			.build());
    }
    
    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody LoginMemberRequest member) {
    	var members = memberService.findByUserId(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 계정입니다."));
        if (!passwordEncoder.matches(member.getPassword(), members.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(members.getUsername(), members.getRoles());
    }
    
    @Data
    static class CreateMemberRequest{
    	private String id;
    	private String companyEmail;
    	private String password;
    }
    
    @Data
    static class LoginMemberRequest{
    	private String id;
    	private String password;
    }
}
