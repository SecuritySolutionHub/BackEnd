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

import com.java.web.solutionhub.board.domain.BoardCategory;
import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.domain.CategoryDto;
import com.java.web.solutionhub.board.service.BoardService;
import com.java.web.solutionhub.board.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	private final CategoryService categoryService;
	
	
	@GetMapping("/board/{id}/content")
	public BoardDto getBoardInfoByIdx(@PathVariable("id") Long id) {
		return boardService.getBoardInfoByIdx(id);
	}
	
	@PostMapping("/board")
	public Long uploadPost(@RequestBody Map<String, String> postInfo) {
		BoardDto uploadData = BoardDto.builder()
				.userId(postInfo.get("userId"))
				.title(postInfo.get("title"))
				.content(postInfo.get("content"))
				.build();
		return boardService.uploadPost(uploadData);
	}
	
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
	
	@PostMapping("/board/{id}")
	public void addCategory(@PathVariable("id") Long id,  @RequestBody Map<String, String> category) {
		boardService.addBoardCategory(id, Long.parseLong(category.get("id")));
	}
}