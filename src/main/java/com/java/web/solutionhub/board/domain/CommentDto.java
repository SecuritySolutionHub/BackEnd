package com.java.web.solutionhub.board.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
	private Long id;
	private Long boardId;
	private Long userId;
	private Long parentsId;
	private Double point;
	private String commentInfo;
	
}
