package com.java.web.solutionhub.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.domain.ReviewDto;
import com.java.web.solutionhub.board.service.ReviewService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	/* STATIC CLASS */
	@Data
	static class RequestReviewDto{
		private Long boardId;
		
		private Long userId;
		
		private Long point;
		
		private String totalReview;
		
		private String advantage;
		
		private String weakness;
	}
	
	@Data
	static class UpdateReviewDto{
		private Long reviewId;
		
		private Long point;
		
		private String totalReview;
		
		private String advantage;
		
		private String weakness;
	}
	
	/* GET */
	@GetMapping("/board/review/{reviewId}")
	public ReviewDto getReviewInfo(@PathVariable("reviewId") Long reviewId) {
		return reviewService.getReviewByReviewId(reviewId);
	}
	
	
	/* POST */
	@PostMapping("/board/review")
	public Long saveReview(@RequestBody RequestReviewDto review) {
		
		return reviewService.saveReview(ReviewDto.builder()
				.boardId(review.boardId)
				.userId(review.userId)
				.point(review.point)
				.totalReview(review.totalReview)
				.advantage(review.advantage)
				.weakness(review.weakness)
				.build());
	}
	
	/*PUT*/
	@PutMapping("/board/review")
	public void updateReview(@RequestBody UpdateReviewDto updateReview) {
		ReviewDto review = ReviewDto.builder()
				.id(updateReview.getReviewId())
				.point(updateReview.getPoint())
				.totalReview(updateReview.getTotalReview())
				.weakness(updateReview.getWeakness())
				.advantage(updateReview.getAdvantage())
				.build();
		reviewService.updateReview(review);
	}
	
	/* DELETE */
	@DeleteMapping("/board/review")
	public void deletereview(@RequestBody ReviewDto review) {
		reviewService.deleteReview(review);
	}
}
