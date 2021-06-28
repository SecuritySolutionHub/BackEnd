package com.java.web.solutionhub.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.Category;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
	List<BoardCategory> findByCategory(Category category);
	List<BoardCategory> findByBoard(Long boardId);
}