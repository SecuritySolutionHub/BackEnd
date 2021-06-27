package com.java.web.solutionhub.board.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	
	private Long categoryId;
	
	@Builder.Default
	private Long parentsId = (long) 0;
	
	private String categoryType;
}
