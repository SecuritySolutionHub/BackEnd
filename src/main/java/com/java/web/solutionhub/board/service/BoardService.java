package com.java.web.solutionhub.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.BoardDto;
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
public class BoardService {
	private final BoardRepository boardRepository;
	private final CategoryRepository categoryRepository;
	private final BoardCategoryRepository bcRepository;
	
	public Long uploadPost(BoardDto boardDto){
		var result = boardRepository.save(boardDto.convertEntity());
		log.info("Post is upload. Title is {}", boardDto.getTitle());
		return result.getId();
	}
	
	public BoardDto getBoardInfoByIdx(Long idx) {
		var getData = boardRepository.getOne(idx);
		List<BoardCategory> categoryList = bcRepository.findByBoard(idx);
		List<Map<Long, String>> bcCate = new ArrayList<>();
		BoardDto result = BoardDto.builder()
				.id(getData.getId())
				.title(getData.getTitle())
				.content(getData.getContent())
				.build();
		
		for(BoardCategory mappingInfo : categoryList) {
			bcCate = makeCategories(mappingInfo.getCategory(), bcCate);
		}
		
		result.setCategories(bcCate);
		return result;
	}
	
	public List<Board> getBoardInfoByTitle(String title) {
		List<Board> getData = boardRepository.findByTitle(title).stream().collect(Collectors.toList());
		return getData;
	}
	
	public List<Board> getBoardInfoByUserId(String userId) {
		List<Board> getData = boardRepository.findByUserId(userId).stream().collect(Collectors.toList());
		return getData;
	}
	
	public void addBoardCategory(Long boardId, Long categoryId) {
		var category = categoryRepository.findById(categoryId).orElse(null);
		var board = boardRepository.findById(boardId).orElse(null);
		var boardCategory = BoardCategory.builder()
				.board(board)
				.category(category)
				.build();
		bcRepository.save(boardCategory);
	}
	
	public void deletePost(Long id) {
		boardRepository.deleteById(id);
	}
	
	public void modifyPost(BoardDto boardDto) {
		var board = boardRepository.getOne(boardDto.getId());
		board.modifyContent(boardDto.getTitle(), boardDto.getContent());
	}
	
	private List<Map<Long, String>> makeCategories(Category info, List<Map<Long, String>> result) {
		Map<Long, String> categoryMapping = new HashMap<>();
		categoryMapping.put(info.getId(), info.getCategoryType());
		result.add(categoryMapping);
		if(info.getChild() != null) {
			result = makeCategories(info.getChild(), result);
		}
		return result;
	}
}
