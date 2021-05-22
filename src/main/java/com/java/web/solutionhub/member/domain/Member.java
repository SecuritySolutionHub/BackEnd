package com.java.web.solutionhub.member.domain;

import javax.persistence.*;

@Entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long idx;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String name;

	@Column(name = "company_mail")
	private String companyEmail;

	@Column(name = "user_grade")
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
}
