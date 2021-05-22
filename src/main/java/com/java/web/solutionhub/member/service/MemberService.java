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
        // 중복 회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getIdx();
    }

    /**
     * 중복 회원 체크
     * @param member
     * @return
     * */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserId(member.getUserId())
                .ifPresent((Member m) -> {
                    throw new IllegalStateException("이미 존재하는 ID 입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return memberRepository
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long idx) {
        return memberRepository.findByIdx(idx);
    }
}
