package com.java.web.solutionhub.repository;

import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.repository.MemberRepository;
import com.java.web.solutionhub.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("TEST USER");
        member.setPassword("1234");
        member.setUserId("userId");
        repository.save(member);

        Member result = repository.findByIdx(member.getIdx()).get();
        Assertions.assertEquals(member, result);
    }

    @Test
    void findByName() {
        Member firstMember = new Member();
        firstMember.setName("First");
        firstMember.setPassword("1234");
        firstMember.setUserId("firstUserId");
        repository.save(firstMember);

        Member secondMember = new Member();
        secondMember.setName("Second");
        repository.save(secondMember);

        Member result = repository.findByName("Second").get();

        Assertions.assertEquals(secondMember, result);
    }
}
