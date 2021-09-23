package com.java.web.solutionhub.member.domain;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberDto {

	
	@NotEmpty(message = "이름값은 필수정보 입니다.")
	String userId;
	
	@NotEmpty(message = "비밀번호는 필수정보 입니다.")
	String password;
	
	@NotEmpty(message = "회사 이메일은 필수정보 입니다.")
	String companyEmail;
}
