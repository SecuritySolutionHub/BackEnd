package com.java.web.solutionhub.member.controller;


import com.java.web.solutionhub.config.jwt.JwtTokenProvider;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

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
    public Long join(@RequestBody Map<String, String> member) {
    	return memberService.join(MemberSaveRequsetDto.builder()
    			.userId(member.get("userId"))
    			.password(passwordEncoder.encode(member.get("password")))
    			.companyEmail(member.get("companyEmail"))
    			.roll(memberRoll.USER)
    			.build());
    }
    
    // �α���
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> member) {
    	var members = memberService.findByUserId(member.get("userId"))
                .orElseThrow(() -> new IllegalArgumentException("���Ե��� ���� �����Դϴ�."));
        if (!passwordEncoder.matches(member.get("password"), members.getPassword())) {
            throw new IllegalArgumentException("�߸��� ��й�ȣ�Դϴ�.");
        }
        return jwtTokenProvider.createToken(members.getUsername(), members.getRoles());
    }
    
    @Data
    static class CreateMemberRequest{
    	private Long id;
    	
    	public CreateMemberRequest(Long id) {
    		this.id = id;
    	}
    }
}
