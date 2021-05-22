package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> storeInfo = new HashMap<>();
	private long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setIdx(++sequence);
		storeInfo.put(member.getIdx(), member);
		return member;
	}
	
	@Override
	public Optional<Member> findByIdx(Long idx) {
		return Optional.ofNullable(storeInfo.get(idx));
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		return storeInfo.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public Optional<Member> findByUserId(String id) {
		return storeInfo.values().stream()
				.filter(member -> member.getUserId().equals(id))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(storeInfo.values());
	}

	public void clearStore() {
		storeInfo.clear();
	}
}
