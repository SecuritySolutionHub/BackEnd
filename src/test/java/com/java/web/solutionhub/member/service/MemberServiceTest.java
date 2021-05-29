package com.java.web.solutionhub.member.service;

import com.java.web.solutionhub.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    Logger logger = LoggerFactory.getLogger(MemberServiceTest.class.getName());

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void clearMemory() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = Member.builder()
        		.userId("kmy2034@naver.com")
        		.name("Min Yong Kim")
        		.passWord("1q2w3e%!!*")
        		.build();

        //when
        Long idx = memberService.join(member);


        //then
        Member findMember = memberService.findOne(idx).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void JoinMember() {
        //given
        Member member1 = Member.builder()
        		.userId("kmy2034@naver.com")
        		.name("Min Yong Kim")
        		.passWord("1q2w3e%!!*")
        		.build();

        Member member2 = Member.builder()
        		.userId("kmy2034@naver.com")
        		.name("Min Yong Kim")
        		.passWord("1q2w3e%!!*")
        		.build();

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}