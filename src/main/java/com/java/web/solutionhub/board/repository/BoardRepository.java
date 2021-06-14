package com.java.web.solutionhub.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	public Board findOne(Long id);
	
	public List<Board> findAll();

	public List<Board> findByTitle(String title);
	
	public List<Board> findByUserId(String userId);
}
