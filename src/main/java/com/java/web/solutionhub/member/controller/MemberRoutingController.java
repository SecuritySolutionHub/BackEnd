package com.java.web.solutionhub.member.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;
import com.java.web.solutionhub.member.dto.RequestRegisterMember;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberRoutingController {

	private final MemberService memberService;
	
	
	@GetMapping("/auth/register")
	public String routeRegister(Model model) {
		model.addAttribute("RegisterMember", new RequestRegisterMember());
		return "/auth/register";
	}
	
	@GetMapping("/auth/password")
	public String routePassword() {
		return "/auth/password";
	}
	
	@PostMapping(value = "/auth/register")
	public String addMember(@Valid RequestRegisterMember form, BindingResult result) {
		log.info("Email is {}", form.getCompanyEmail());
		log.info("test information");
		log.debug("Email is {}", form.getCompanyEmail());
		log.debug("test information");
		
		MemberSaveRequsetDto member = MemberSaveRequsetDto.builder()
				.userId(form.getUserName())
				.companyEmail(form.getCompanyEmail())
				.password(form.getPassWord())
				.roll(memberRoll.USER)
				.build();
		memberService.join(member);
		return "redirect:/";
	}
}
