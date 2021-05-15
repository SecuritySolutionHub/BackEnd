package com.java.web.solutionHub.member.domain;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
	private Long idx;
	
	private String userId;
	private String password;
	private String name;
	private String companyEmail;

	private int bAdmin;
}
