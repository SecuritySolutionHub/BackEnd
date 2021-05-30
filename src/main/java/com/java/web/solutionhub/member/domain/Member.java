package com.java.web.solutionhub.member.domain;


import javax.persistence.*;

import com.java.web.solutionhub.member.domain.enum_package.MemberStatic;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long idx;

	@Column(name = "userId" , nullable = false)
	private String userId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "companyEmail")
	private String companyEmail;

	@Column(name = "memberRoll", nullable = false)
	@Enumerated(EnumType.STRING)
	private MemberStatic.memberRoll memberRoll;

	@Column(name = "createDate")
	private LocalDateTime createDate;

}
