package com.java.web.solutionhub.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardDto {
	
	
	private Long id;
	
	private String title;
	
	private String content;
	
	private Long categoryId;
	
	private Long commentId;
}
