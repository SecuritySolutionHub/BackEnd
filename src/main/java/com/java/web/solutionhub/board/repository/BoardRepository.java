package com.java.web.solutionhub.board.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.java.web.solutionhub.board.domain.Board;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
	
	private final EntityManager em;
	
	public Long save(Board board) {
		em.persist(board);
		return board.getId();
	}
	
	public Board findOne(Long id) {
		return em.find(Board.class, id);
	}
	
	public List<Board> findAll() {
		return em.createQuery("select b from board as b", Board.class).getResultList();
	}
}
