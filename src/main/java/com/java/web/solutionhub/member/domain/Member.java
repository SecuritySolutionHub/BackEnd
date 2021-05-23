package com.java.web.solutionhub.member.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.util.Assert;

import lombok.Builder;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public void setbAdmin(int bAdmin) {
		this.bAdmin = bAdmin;
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
