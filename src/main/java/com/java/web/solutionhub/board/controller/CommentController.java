package com.java.web.solutionhub.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.web.solutionhub.board.domain.CommentDto;
import com.java.web.solutionhub.board.service.CommentService;

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
	public Long getBoardComment(@PathVariable("boardId") Long boardId,
			@RequestBody @Valid CreateCommentResponse postInfo) {
		CommentDto commentDto = CommentDto.builder()
				.userId(postInfo.getUserId())
				.parentsId(postInfo.getParentsId())
				.commentInfo(postInfo.getCommentInfo())
				.build();
		
		return commentService.saveComment(commentDto, boardId);
	}
	
	@DeleteMapping("comment/{boardId}")
	public void deleteBoardComment(@PathVariable("boardId") Long boardId, @RequestBody CreateCommentResponse deleteInfo) {
		CommentDto commentDto = CommentDto.builder()
				.userId(deleteInfo.getUserId())
				.parentsId(deleteInfo.getParentsId())
				.commentInfo(deleteInfo.getCommentInfo())
				.build();
		
		commentService.deleteComment(commentDto);
	}
	
	
	@Data
	static class CreateCommentResponse {
		private Long userId;
		private Long parentsId;
		private String commentInfo;
		
		@JsonCreator 
		public CreateCommentResponse(@JsonProperty("userId") Long userId, 
				@JsonProperty("parentsId") Long parentsId, 
				@JsonProperty("commentInfo") String commentInfo) { 
			this.userId = userId; 
			this.parentsId = parentsId; 
			this.commentInfo = commentInfo; 
		}

		
	}
}
