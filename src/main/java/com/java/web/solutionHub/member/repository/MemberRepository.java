package com.java.web.solutionHub.member.repository;

import com.java.web.solutionHub.member.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository{
	Member save(Member member);
	Optional<Member> findByIdx(Long idx);
	Optional<Member> findByName(String name);
	Optional<Member> findByUserId(String Id);
	List<Member> findAll();
}
