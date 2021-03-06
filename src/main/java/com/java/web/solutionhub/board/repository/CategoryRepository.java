package com.java.web.solutionhub.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Optional<Category> findByCategoryType(String categoryType);
}
