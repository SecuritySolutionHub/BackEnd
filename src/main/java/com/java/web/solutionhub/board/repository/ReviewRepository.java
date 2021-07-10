package com.java.web.solutionhub.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	@Query(value = "SELECT avg(point) FROM Review r WHERE r.board = :board")
	public Long getAvgPointByBoard(@Param("board") Board board);
	
	public List<Review> findByBoard(Board board);
	
}
