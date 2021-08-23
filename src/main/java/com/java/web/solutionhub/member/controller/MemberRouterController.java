package com.java.web.solutionhub.member.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.web.solutionhub.member.domain.MemberDto;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberRouterController {
	
	private final MemberService memberService;
	
	@GetMapping(value = "/member/register")
	public String createForm(Model model) {
		model.addAttribute("MemberDto", new MemberDto());
		return "/member/register";
		
	}
	
	@PostMapping(value = "/member/register")
	public String addMember(@Valid MemberDto form, BindingResult result) {
		log.info("Email is {}", form.getUserId());
		log.info("test information");
		System.out.println(form.getUserId());
		return "redirect:/";
	}
}
