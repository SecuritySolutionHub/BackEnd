package com.java.web.solutionhub.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.BoardDto;
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
public class BoardService {
	private final BoardRepository boardRepository;
	private final CategoryRepository categoryRepository;
	private final BoardCategoryRepository boardCategoryRepository;
	
	
	public Long uploadPost(BoardDto boardDto){
		var result = boardRepository.save(boardDto.convertEntity());
		log.info("Post is upload. Title is {}", boardDto.getTitle());
		return result.getId();
	}
	
	public BoardDto getBoardInfoByIdx(Long idx) {
		Board getData = boardRepository.findById(idx).orElseThrow();
		List<CategoryDto> categories = new ArrayList<>();
		for(BoardCategory boardCategory : getData.getBoardCategory()) {
			var category = new CategoryDto();
			category.setCategoryId(boardCategory.getCategory().getId());
			category.setCategoryType(boardCategory.getCategory().getCategoryType());
			if(boardCategory.getCategory().getParent() != null) {
				category.setParentsId(boardCategory.getCategory().getParent().getId());
			}
			categories.add(category);
		}
		return BoardDto.builder()
				.title(getData.getTitle())
				.userId(getData.getUserId())
				.id(getData.getId())
				.content(getData.getContent())
				.categories(categories)
				.build();
	}
	
	public List<Board> getBoardInfoByTitle(String title) {
		return boardRepository.findByTitle(title).stream().collect(Collectors.toList());
	}
	
	public List<Board> getBoardInfoByUserId(String userId) {
		return boardRepository.findByUserId(userId).stream().collect(Collectors.toList());
	}
	
	public void addBoardCategory(Long boardId, Long categoryId) {
		var category = categoryRepository.findById(categoryId).orElse(null);
		var board = boardRepository.findById(boardId).orElse(null);
		var boardCategory = BoardCategory.builder()
				.board(board)
				.category(category)
				.build();
		boardCategoryRepository.save(boardCategory);
	}
	
	/**
	 * delete Board function
	 * @param id
	 */
	public void deletePost(Long id) { 	
		boardRepository.deleteById(id);
	}
	
	/**
	 * category Id 기반의 board 정보 조회
	 * @param cagetoryId
	 * @return
	 */
	public List<BoardDto> getBoardInfoByCategory(Long cagetoryId) {
		List<BoardCategory> getBoardData = boardCategoryRepository.findByCategory(
				categoryRepository.findById(cagetoryId).orElseThrow());
		List<BoardDto> resultList = new ArrayList<>();
		for(BoardCategory boardCategory : getBoardData) {
			var board = boardCategory.getBoard();
			resultList.add(getBoardInfoByIdx(board.getId()));
		}
		
		return resultList;
	}
	
	/**
	 * board 정보 수정
	 * @param boardDto
	 */
	@Transactional
	public void modifyPost(BoardDto boardDto) {
		var board = boardRepository.getOne(boardDto.getId());
		board.modifyContent(boardDto.getTitle(), boardDto.getContent());
	}
	
}
