package com.java.web.solutionhub.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.Category;
import com.java.web.solutionhub.board.domain.CategoryDto;
import com.java.web.solutionhub.board.repository.BoardCategoryRepository;
import com.java.web.solutionhub.board.repository.BoardRepository;
import com.java.web.solutionhub.board.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {
	private final BoardRepository boardRepository;
	private final CategoryRepository categoryRepository;
	private final BoardCategoryRepository boardCategoryRepository;
	
	@Transactional
	public Long addCategory(String categoryType) {
		var category = Category.builder()
				.categoryType(categoryType)
				.build();
		var result = categoryRepository.save(category);
		log.info("category save {}", categoryType);
		return result.getId();
	}
	
	@Transactional
	public void addChildCategoryToBoard(Long boardId, Long parentsId, Long childId) {
		var board = boardRepository.findById(boardId).orElseThrow();
		var child = categoryRepository.findById(childId).orElseThrow();
		var parents = categoryRepository.findById(parentsId).orElseThrow();
		
		parents.addChildCategory(child);
		
		BoardCategory saveData = BoardCategory.builder()
				.board(board)
				.category(child)
				.build();
		
		boardCategoryRepository.save(saveData);
	}
	
	public CategoryDto findCategoryById(Long id) {
		var category = categoryRepository.findById(id).orElseThrow();
		CategoryDto result = CategoryDto.builder()
				.categoryId(category.getId())
				.categoryType(category.getCategoryType())
				.build();
		
		if(category.getParent() != null) {
			result.setParentsId(category.getParent().getId());
		}
		
		return result;
	}
	
	public CategoryDto findCategoryByCategoryType(String categoryType) {
		var category = categoryRepository.findByCategoryType(categoryType).orElseThrow();
		
		CategoryDto result = CategoryDto.builder()
				.categoryId(category.getId())
				.categoryType(category.getCategoryType())
				.build();
		
		if(category.getParent() != null) {
			result.setParentsId(category.getParent().getId());
		}
		
		return result;
	}
	
}
