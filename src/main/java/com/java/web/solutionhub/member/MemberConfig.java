package com.java.web.solutionhub.member;

import com.java.web.solutionhub.member.repository.JpaMemberRepository;
import com.java.web.solutionhub.member.repository.MemberRepository;
import com.java.web.solutionhub.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class MemberConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
