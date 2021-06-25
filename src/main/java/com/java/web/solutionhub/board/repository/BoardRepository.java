package com.java.web.solutionhub.board.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	public List<Board> findByUserId(String userId);
	
	public Optional<Board> findByTitle(String title);
}
