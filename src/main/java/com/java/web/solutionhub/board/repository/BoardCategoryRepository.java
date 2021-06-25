package com.java.web.solutionhub.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.BoardCategory;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
	List<BoardCategory> findByCategory(Long categoryId);
	List<BoardCategory> findByBoard(Long boardId);
}
