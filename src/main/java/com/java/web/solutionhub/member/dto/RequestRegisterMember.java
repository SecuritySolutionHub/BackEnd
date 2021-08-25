package com.java.web.solutionhub.member.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RequestRegisterMember {
	
	@NotEmpty(message = "�̸��� �ʼ� ���� �Դϴ�.")
	String userName;
	
	@NotEmpty(message = "��й�ȣ�� �ʼ� ���� �Դϴ�.")
	String passWord;
	
	@NotEmpty(message = "ȸ������� �ʼ� ���� �Դϴ�.")
	String companyEmail;
	
}
