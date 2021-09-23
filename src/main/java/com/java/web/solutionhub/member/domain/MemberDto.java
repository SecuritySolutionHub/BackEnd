package com.java.web.solutionhub.member.domain;


import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberDto {

	
	@NotEmpty(message = "�̸����� �ʼ����� �Դϴ�.")
	String userId;
	
	@NotEmpty(message = "��й�ȣ�� �ʼ����� �Դϴ�.")
	String password;
	
	@NotEmpty(message = "ȸ�� �̸����� �ʼ����� �Դϴ�.")
	String companyEmail;
}
