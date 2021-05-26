package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
class MemoryMemberRepositoryTest {
	
	EntityManager em;
	
    JpaMemberRepository repository;

    @Autowired
    MemoryMemberRepositoryTest(EntityManager em) {
    	this.repository = new JpaMemberRepository(em);
    }
    
    @AfterEach
    void afterEach() {
    	
    }

    @Test
    void save() {
        Member member = Member.builder()
        		.name("TEST USER")
        		.passWord("1234")
        		.userId("userId")
        		.build();
        repository.save(member);

        Member result = repository.findByIdx(member.getIdx()).get();
        Assertions.assertEquals(member, result);
    }

    @Test
    void findByName() {
        Member firstMember = Member.builder()
        		.name("TEST USER")
        		.passWord("1234")
        		.userId("userId")
        		.build();

        repository.save(firstMember);

        Member secondMember = Member.builder()
        		.userId("userID")
        		.passWord("password")
        		.name("Second")
        		.build();
        repository.save(secondMember);

        Member result = repository.findByName("Second").get();

        Assertions.assertEquals(secondMember, result);
    }
}
