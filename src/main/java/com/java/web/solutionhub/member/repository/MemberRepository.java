package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByIdx(Long idx);
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByUserIdAndPassWord(String userId, String passWord);
	
	List<Member> findAll();
	Page<Member> findAll(Pageable pageAble);
	List<Member> findByMemberRoll(memberRoll memberRoll);
	Page<Member> findByMemberRoll(memberRoll memberRoll, Pageable pageAble);
}
