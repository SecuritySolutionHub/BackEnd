package com.java.web.solutionhub.board.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewDto {
	
	private Long id;
	
	private Long boardId;
	
	private Long userId;
	
	private Long point;
	
	private String totalReview;
	
	private String advantage;
	
	private String weakness;

	private LocalDateTime time;
}
