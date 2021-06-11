package com.java.web.solutionhub.member.repository;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class JpaMemberRepositoryTest {

	@Autowired
	JpaMemberRepository jpaMemberRepository;
	
	
	@Test
	void joinMember() {
		//given
		MemberSaveRequsetDto dto = MemberSaveRequsetDto.builder()
						.userId("member@test.com")
						.password("1234")
						.companyEmail("test@gmail.com")
						.roll(memberRoll.USER)
						.build();
		
		//when
		jpaMemberRepository.save(dto.toEntity());
		Member member = jpaMemberRepository.findByUserId("member@test.com").get();
		
		//then
		assertEquals(member.getUserId(), "member@test.com");
	}

}
