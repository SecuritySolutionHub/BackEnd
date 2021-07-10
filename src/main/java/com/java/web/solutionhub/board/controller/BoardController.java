package com.java.web.solutionhub.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.domain.CategoryDto;
import com.java.web.solutionhub.board.domain.ReviewDto;
import com.java.web.solutionhub.board.service.BoardService;
import com.java.web.solutionhub.board.service.CategoryService;
import com.java.web.solutionhub.board.service.ReviewService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final ReviewService reviewService;
	private final CategoryService categoryService;
	
	@Data
	@AllArgsConstructor
	private class BoardInfo {
		BoardDto board;
		Long avgPoint = (long) 0;
	}
	
	@Data
	@AllArgsConstructor
	private class BoardContentInfo{
		BoardDto board;
		Long avgPoint = (long) 0;
		List<ReviewDto> reviewList;
	}
	
	/* GET */
	
	/**
	 * board 고유 ID 기반의 내용 조회
	 * @param id
	 * @return
	 */
	@GetMapping("/board/{boardId}/content")
	public BoardContentInfo getBoardInfoByIdx(@PathVariable("boardId") Long id) {
		var board = boardService.getBoardInfoByIdx(id);
		var reviewInfo = reviewService.getReviewListByBoard(id);
		return new BoardContentInfo(board, reviewInfo.getAvgPoint(), reviewInfo.getReviewDtoList());
	}
	
	@GetMapping("/board/{categoryId}/category")
	public List<BoardDto> getBoardInfoByCategory(@PathVariable("categoryId") Long id) {
		return boardService.getBoardInfoByCategory(id);
	}
	
	@GetMapping("/category/{categoryId}")
	public CategoryDto getCategoryInfoByid(@PathVariable("categoryId") Long id) {
		return categoryService.findCategoryById(id);
	}
	
	@GetMapping("/board/{category}")
	public List<BoardInfo> getBoardInfoByCategory(@PathVariable("category") String category) {
		CategoryDto cate = categoryService.findCategoryByCategoryType(category);
		var boardDtoList = boardService.getBoardInfoByCategory(cate.getCategoryId());
		List<BoardInfo> result = new ArrayList<>();
		for(BoardDto boardDto : boardDtoList) {
			var boardInfo = new BoardInfo(boardDto, reviewService.getReviewListByBoard(boardDto.getId()).getAvgPoint());
			result.add(boardInfo);
		}
		return result;
	}
	
	/* POST */
	@PostMapping("/board")
	public Long uploadPost(@RequestBody Map<String, String> postInfo) {
		BoardDto uploadData = BoardDto.builder()
				.userId(postInfo.get("userId"))
				.title(postInfo.get("title"))
				.content(postInfo.get("content"))
				.build();
		return boardService.uploadPost(uploadData);
	}
	
	@PostMapping("/board/{id}")
	public void addCategory(@PathVariable("id") Long id,  @RequestBody Map<String, String> category) {
		boardService.addBoardCategory(id, Long.parseLong(category.get("id")));
	}
	
	/* PUT */
	@PutMapping("/board/{id}/content")
	public void modifyBoardInfoByIdx(@PathVariable("id") Long id, @RequestBody Map<String, String> postInfo) {
		BoardDto modifyData = BoardDto.builder()
				.id(id)
				.userId(postInfo.get("userId"))
				.title(postInfo.get("title"))
				.content(postInfo.get("content"))
				.build();
		
		boardService.modifyPost(modifyData);
	}
	
	@DeleteMapping("/board/{id}/content")
	public void deleteBoardInfoByIdx(@PathVariable("id") Long id) {
		boardService.deletePost(id);
	}
	
	
}