package com.java.web.solutionhub.board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.Category;
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
	
	public Category findCategoryById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElse(null);
	}
	
	public void addChildCategoryToBoard(Long boardId, Long parentsId, Long childId) {
		var board = boardRepository.findById(boardId).orElseThrow();
		var parents = categoryRepository.findById(parentsId).orElseThrow();
		var child = categoryRepository.findById(childId).orElseThrow();

		parents.addChildCategory(child);
		BoardCategory saveData = BoardCategory.builder()
				.board(board)
				.category(child)
				.build();
		
		boardCategoryRepository.save(saveData);
	}
}
