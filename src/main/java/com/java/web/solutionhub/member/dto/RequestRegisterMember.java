package com.java.web.solutionhub.member.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RequestRegisterMember {
	
	@NotEmpty(message = "이름은 필수 정보 입니다.")
	String userName;
	
	@NotEmpty(message = "비밀번호는 필수 정보 입니다.")
	String passWord;
	
	@NotEmpty(message = "회사메일은 필수 정보 입니다.")
	String companyEmail;
	
}
