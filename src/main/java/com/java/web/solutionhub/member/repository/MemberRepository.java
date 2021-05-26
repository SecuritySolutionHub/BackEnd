package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository{
	Member save(Member member);
	Optional<Member> findByIdx(Long idx);
	Optional<Member> findByName(String name);
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByUserIdAndPassWord(String userId, String passWord);
	List<Member> findAll();
}
