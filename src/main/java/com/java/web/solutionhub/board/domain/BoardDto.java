package com.java.web.solutionhub.board.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {
	
	private Long id;
	
	private String title;
	
	private String content;
	
	public Board convertEntity() {
		return Board.builder()
				.title(title)
				.content(content)
				.build();
	}
}
