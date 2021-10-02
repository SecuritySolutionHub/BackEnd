package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	Optional<Member> findByIdx(Long idx);
	Optional<Member> findByUserId(String userId);
	Optional<Member> findByUserIdAndPassWord(String userId, String passWord);
	Optional<Member> findByMemberRoll(String memberRoll);
	List<Member> findAll(Pageable pageAble);
	List<Member> findAll();
}
