package com.java.web.solutionhub.member.domain.enum_package;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public class MemberStatic {

	@Getter
	@AllArgsConstructor
	@RequiredArgsConstructor
	public enum memberRoll {
		USER("NORMAL_USER", "인증되지 않은 회원"),
		AUTHUSER("AUTH_USER", "인증된 회원"),
		COMPANYUSER("COMPANY_USER", "기업 회원"),
		MANAGER("MANAGER", "매니저"),
		ADMIN("ADMIN", "관리자");

		private String key = "";
		private String value = "";
	}
}