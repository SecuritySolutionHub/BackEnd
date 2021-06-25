package com.java.web.solutionhub.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Category;
import com.java.web.solutionhub.board.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	@Transactional
	public Long addCategory(String categoryType) {
		var category = Category.builder()
				.categoryType(categoryType)
				.build();
		var result = categoryRepository.save(category);
		log.info("category save {}", categoryType);
		return result.getId();
	}
	
	public Category findCategoryById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElse(null);
	}
	
	public void addChildCategory(Long parentsId, Long childId) {
		Optional<Category> parentsCategory = categoryRepository.findById(parentsId);
		Optional<Category> childCategory = categoryRepository.findById(childId);
		
		var parents = parentsCategory.orElseThrow();
		var child = childCategory.orElse(null);
		
		parents.addChildCategory(child);
	}
}
