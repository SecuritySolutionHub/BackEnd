package com.java.web.solutionhub.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BoardDto {
	
	private Long id;
	
	private String userId;
	
	private String title;
	
	private String content;
	
	public Board convertEntity() {
		return Board.builder()
				.userId(userId)
				.title(title)
				.content(content)
				.build();
	}
}
