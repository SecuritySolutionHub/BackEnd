package com.java.web.solutionHub.member.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MemberStatic {
	
	@Getter
	@AllArgsConstructor
	public enum bAdmin {
		NOLMAL(0), ADMIN(1);
		private int value;
		bAdmin(int i) {
			this.value = i;
		}
	}
}
