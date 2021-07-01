package com.java.web.solutionhub.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.domain.CommentDto;
import com.java.web.solutionhub.board.service.CommentService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@GetMapping("comment/{boardId}")
	public List<CommentDto> getBoardComment(@PathVariable("boardId") Long boardId) {
		return commentService.getCommentListByBoardId(boardId);
	}
	
	@PostMapping("comment/{boardId}")
	public Long getBoardComment(@PathVariable("boardId") Long boardId, @RequestBody CreateCommentResponse postInfo) {
		CommentDto commentDto = CommentDto.builder()
				.userId(postInfo.getUserId())
				.parentsId(postInfo.getParentsId())
				.commentInfo(postInfo.getCommentInfo())
				.build();
		
		return commentService.saveComment(commentDto, boardId);
	}
	
	
	@Data
	@AllArgsConstructor
	static class CreateCommentResponse {
		private Long userId;
		private Long parentsId;
		private String commentInfo;
	}
}
