package com.java.web.solutionhub.member.repository;

import com.java.web.solutionhub.member.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findByIdx(Long idx) {
        var member = em.find(Member.class, idx);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        List<Member> result = em.createQuery("select m from Member as m where m.user_id = :id", Member.class)
                .setParameter(":id", userId)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByUserIdAndPassWord(String userId, String passWord) {
    	List<Member> result = em.createQuery("select m from Member as m where m.user_id = :id and m.password = :pass", Member.class)
    			.setParameter(":id", userId)
    			.setParameter(":pass", passWord)
    			.getResultList();
    	
    	return result.stream().findAny();
    }
    
    @Override
	public Optional<Member> findByMemberRoll(String memberRoll) {
    	List<Member> result = em.createQuery("select m from Member as m where m.memberRoll = roll", Member.class)
    			.setParameter("roll", memberRoll)
    			.getResultList();
    	
    	return result.stream().findAny();
    }
    
    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    
    
    
}
