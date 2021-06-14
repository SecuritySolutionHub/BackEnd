package com.java.web.solutionhub.board.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.java.web.solutionhub.board.domain.Category;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
	
	private final EntityManager em;
	
	public void save(Category category) {
		em.persist(category);
	}
}
