package com.java.web.solutionhub.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.domain.CommentDto;
import com.java.web.solutionhub.board.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("comment/{boardId}")
	public List<CommentDto> getBoardComment(@PathVariable("boardId") Long boardId) {
		return commentService.getCommentListByBoardId(boardId);
	}
}
