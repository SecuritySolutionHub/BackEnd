package com.java.web.solutionhub.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.domain.ReviewDto;
import com.java.web.solutionhub.board.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;
	
	/* STATIC CLASS */
	
	/* GET */
	
	/* POST */
	@PostMapping("/board/review")
	public Long saveReview(ReviewDto review) {
		return reviewService.saveReview(review);
	}
	
	/*PUT*/
	@PutMapping("/board/review")
	public void updateReview(ReviewDto review) {
		reviewService.updateReview(review);
	}
	
	/* DELETE */
	@DeleteMapping("/board/review")
	public void deletereview(ReviewDto review) {
		reviewService.deleteReview(review);
	}
}
