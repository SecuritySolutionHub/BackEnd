package com.java.web.solutionhub.member.domain.enum_package;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


public class MemberStatic {

	@Getter
	@AllArgsConstructor
	@RequiredArgsConstructor
	public enum memberRoll {
		USER("NORMAL_USER", "인증되지 않은 사용자"),
		AUTHUSER("AUTH_USER", "인증된 사용자"),
		COMPANYUSER("COMPANY_USER", "회사계정 사용자"),
		MANAGER("MANAGER", "매니저"),
		ADMIN("ADMIN", "관리자");

		memberRoll(String key, String value) {
			this.key = key;
			this.value = value;
		}

		private String key = "";
		private String value = "";
	}
}