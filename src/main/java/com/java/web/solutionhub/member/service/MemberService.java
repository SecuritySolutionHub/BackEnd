package com.java.web.solutionhub.member.service;


import com.java.web.solutionhub.member.domain.Member;
import com.java.web.solutionhub.member.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // Dependency Injection(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 중복회원 체크
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getIdx();
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
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserId())
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
}
