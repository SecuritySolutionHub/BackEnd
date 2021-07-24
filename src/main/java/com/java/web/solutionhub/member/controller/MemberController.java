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
    
    
    // ȸ�� ����
    @PostMapping("/join")
    public Long join(@RequestBody CreateMemberRequest member) {
    	return memberService.join(MemberSaveRequsetDto.builder()
    			.userId(member.id)
    			.password(passwordEncoder.encode(member.password))
    			.companyEmail(member.companyEmail)
    			.roll(memberRoll.USER)
    			.build());
    }
    
    // �α���
    @PostMapping("/login")
    public String login(@RequestBody LoginMemberRequest member) {
    	var members = memberService.findByUserId(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("���Ե��� ���� �����Դϴ�."));
        if (!passwordEncoder.matches(member.getPassword(), members.getPassword())) {
            throw new IllegalArgumentException("�߸��� ��й�ȣ�Դϴ�.");
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
