package com.java.web.solutionhub.member.service;


import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;
import com.java.web.solutionhub.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long join(MemberSaveRequsetDto memberDto) {
        validateDuplicateMember(memberDto);
        return memberRepository.save(memberDto.toEntity()).getIdx();
    }

    /**
     * ID와 Password로 회원 확인
     * @param Member
     * 
     * */
    public void checkMember(Member member) {
    	memberRepository.findByUserIdAndPassWord(member.getUserId(), member.getPassword())
    		.ifPresent((Member m) -> {
    			throw new IllegalStateException("회원을 찾을 수 없습니다.");
    		});
    }
    
    
    
    /**
     * 회원 중복 체크
     * @param member
     * @return
     * */
    private void validateDuplicateMember(MemberSaveRequsetDto memberDto) {
        memberRepository.findByUserId(memberDto.getUserId())
                .ifPresent((Member m) -> {
                    throw new IllegalStateException("중복되는 ID가 존재합니다.");
                });
    }

    /**
     * 모든 회원 조회
     * @return memberRepository
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long idx) {
        return memberRepository.findByIdx(idx);
    }
    
    public Optional<Member> findByUserId(String userId) {
    	return memberRepository.findByUserId(userId);
    }
}
