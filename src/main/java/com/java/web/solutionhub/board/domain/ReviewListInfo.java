package com.java.web.solutionhub.board.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewListInfo {
	
	private Long avgPoint;
	
	private List<ReviewDto> reviewDtoList;
}
