package com.java.web.solutionhub.board.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.web.solutionhub.board.domain.ReviewDto;
import com.java.web.solutionhub.board.domain.ReviewListInfo;
import com.java.web.solutionhub.board.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ReviewServiceTest {

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Test
	void reviewUploadTest() {
		
		ReviewDto reviewDto = ReviewDto.builder()
				.id((long) 123)
				.boardId((long)1)
				.advantage("advantage")
				.point((long) 100)
				.totalReview("total")
				.weakness("weakness")
				.build();
		
		Long reviewId = reviewService.saveReview(reviewDto);
		ReviewDto getReview = reviewService.getReviewByReviewId(reviewId);
		
		assertEquals(reviewDto.getAdvantage(), getReview.getAdvantage());
	}
	
	@Test
	void getReviewListByBoard() {
		boardService.getBoardInfoByIdx((long) 1);
		
		ReviewDto reviewDtoFirst = ReviewDto.builder()
				.id((long) 123)
				.boardId((long)1)
				.advantage("advantage")
				.point((long) 100)
				.totalReview("total")
				.weakness("weakness")
				.build();
		
		ReviewDto reviewDtoSecond = ReviewDto.builder()
				.id((long) 123)
				.boardId((long)1)
				.advantage("advantage")
				.point((long) 50)
				.totalReview("total")
				.weakness("weakness")
				.build();
		
		reviewService.saveReview(reviewDtoFirst);
		reviewService.saveReview(reviewDtoSecond);
		
		ReviewListInfo listInfo = reviewService.getReviewListByBoard((long) 1);
		
		log.info("test list is " + listInfo.toString());
	}

}
