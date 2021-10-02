package com.java.web.solutionhub.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.domain.MemberDto;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AdminController {
	
	private final MemberService memberService;
	
	/*============================================ GET METHOD ===============================================*/
	@GetMapping("/admin/member/all")
	public List<MemberDto> getAllMemberInfo() {
		List<Member> memberList = memberService.findMembers();
		List<MemberDto> result = new ArrayList<>();
		
		for(Member member : memberList) {
			result.add(MemberDto.builder()
					.userId(member.getUserId())
					.companyEmail(member.getCompanyEmail())
					.build());
		}
		return result;
	}
	
	@GetMapping("/admin/member/all/{page}")
	public void getAllMemberInfoByPage(@PathVariable("page")int pageNumber) {
		memberService.findMembersPageable(pageNumber);
		return;
	}
	/*============================================ POST METHOD ===============================================*/
	
	
	
	/*============================================ PUT METHOD ===============================================*/
	
	
	
	/*============================================ DELETE METHOD ===============================================*/
}
