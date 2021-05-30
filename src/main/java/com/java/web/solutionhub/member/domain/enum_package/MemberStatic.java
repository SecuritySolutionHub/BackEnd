package com.java.web.solutionhub.member.domain.enum_package;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public class MemberStatic {

	@Getter
	@AllArgsConstructor
	@RequiredArgsConstructor
	public enum memberRoll {
		USER("NORMAL_USER", "�������� ���� ȸ��"),
		AUTHUSER("AUTH_USER", "������ ȸ��"),
		COMPANYUSER("COMPANY_USER", "��� ȸ��"),
		MANAGER("MANAGER", "�Ŵ���"),
		ADMIN("ADMIN", "������");

		private String key = "";
		private String value = "";
	}
}