package com.java.web.solutionhub.member.domain.enum_package;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public class MemberStatic {

	@Getter
	@AllArgsConstructor
	@RequiredArgsConstructor
	public enum memberRoll {
		USER("NORMAL_USER", "USER"),
		AUTHUSER("AUTH_USER", "AUTHUSER"),
		COMPANYUSER("COMPANY_USER", "COMPANY_USER"),
		MANAGER("MANAGER", "MANAGER"),
		ADMIN("ADMIN", "ADMIN");

		private String key = "";
		private String value = "";
	}
}