package com.java.web.solutionHub.member.repository;

import com.java.web.solutionHub.member.domain.Member;

import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
	
	private static Map<Long, Member> store = new HashMap<>();
	private long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		member.setIdx(++sequence);
		store.put(member.getIdx(), member);
		return member;
	}
	
	@Override
	public Optional<Member> findByIdx(Long idx) {
		return Optional.ofNullable(store.get(idx));
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(member -> member.getName().equals(name))
				.findAny();
	}

	@Override
	public Optional<Member> findByUserId(String Id) {
		return Optional.empty();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
}
