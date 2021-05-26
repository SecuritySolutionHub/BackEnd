package com.java.web.solutionhub.member.domain;


import javax.persistence.*;

import org.springframework.util.Assert;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long idx;

	@Column(name = "user_id" , nullable = false)
	private String userId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "username", nullable = false)
	private String name;

	@Column(name = "company_mail")
	private String companyEmail;

	@Column(name = "user_grade", nullable = false)
	private int bAdmin;

	public Long getIdx() {
		return idx;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public int getbAdmin() {
		return bAdmin;
	}

	@Builder
	public Member(Long idx, int bAdmin, String userId, String passWord, String name, String companyEmail) {
		Assert.hasText(userId, "userId must not be empty");
		Assert.hasText(passWord, "passWord must not be empty");
		Assert.hasText(name, "name must not be empty");
		this.idx = idx;
		this.bAdmin = bAdmin;
		this.userId = userId;
		this.password = passWord;
		this.name = name;
		this.companyEmail = companyEmail;
	}
}
