package com.java.web.solutionhub.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
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
	/**
	 * Get all member information
	 * @return List<MemberDto>
	 */
	@GetMapping("/admin/member/all")
	public List<MemberDto> getAllMemberInfo() {
		List<Member> memberList = memberService.findMembers();
		List<MemberDto> result = new ArrayList<>();
		
		for(Member member : memberList) {
			result.add(MemberDto.builder()
					.userId(member.getUserId())
					.companyEmail(member.getCompanyEmail())
					.memberRoll(member.getMemberRoll().getValue())
					.build());
		}
		return result;
	}
	
	/**
	 * Get all member information and page
	 * @param pageNumber
	 * @return 
	 */
	@GetMapping("/admin/member/all/{page}")
	public List<MemberDto> getAllMemberInfoByPage(@PathVariable("page")int pageNumber) {
		Page<Member> getList = memberService.findMembersPageable(pageNumber);
		List<MemberDto> resultList = new ArrayList<>();
		
		for(Member member : getList) {
			resultList.add(MemberDto.builder()
					.userId(member.getUserId())
					.companyEmail(member.getCompanyEmail())
					.memberRoll(member.getMemberRoll().getValue())
					.build());
		}
		
		return resultList;
	}
	
	/**
	 * Get all member information by member roll
	 * @param roll
	 * @return
	 */
	@GetMapping("/admin/member/{roll}")
	public List<MemberDto> getMemberInfoByType(@PathVariable("roll")String roll) {
		List<Member> memberList = memberService.findMembersByMemberRoll(roll);
		List<MemberDto> resultList = new ArrayList<>();
		
		for(Member member : memberList) {
			resultList.add(MemberDto.builder()
					.userId(member.getUserId())
					.companyEmail(member.getCompanyEmail())
					.memberRoll(member.getMemberRoll().getValue())
					.build());
		}
		
		return resultList;
	}
	
	/**
	 * Get all member information by member roll and page
	 * @param roll
	 * @param page
	 * @return
	 */
	@GetMapping("/admin/member/{roll}/{page}")
	public List<MemberDto> getMemberInfoByTypeAndPage(@PathVariable("roll")String roll, @PathVariable("page")int page) {
		Page<Member> getList = memberService.findMemberByMemberRoll(roll, page);
		List<MemberDto> resultList = new ArrayList<>();
		
		for(Member member : getList) {
			resultList.add(MemberDto.builder()
					.userId(member.getUserId())
					.companyEmail(member.getCompanyEmail())
					.memberRoll(member.getMemberRoll().getValue())
					.build());
		}
		
		return resultList;
	}
	
	
	/*============================================ POST METHOD ===============================================*/
	
	
	
	/*============================================ PUT METHOD ===============================================*/
	
	
	
	/*============================================ DELETE METHOD ===============================================*/
}
