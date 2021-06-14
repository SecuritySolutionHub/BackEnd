package com.java.web.solutionhub.board.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.java.web.solutionhub.board.domain.Comment;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
	
	private final EntityManager em;
	
	public void save(Comment comment) {
		em.persist(comment);
	}

}
