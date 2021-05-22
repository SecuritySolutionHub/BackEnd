package com.java.web.solutionhub.member.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;


public class MemberStatic {

	public enum bAdmin {
		NORMAL(0), ADMIN(1);
		private int value;
		bAdmin(int value) {
			this.value = value;
		}
		int getValue(){
			return this.value;
		}
	}
}