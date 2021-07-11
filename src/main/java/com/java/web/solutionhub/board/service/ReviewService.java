package com.java.web.solutionhub.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Review;
import com.java.web.solutionhub.board.domain.ReviewDto;
import com.java.web.solutionhub.board.domain.ReviewListInfo;
import com.java.web.solutionhub.board.repository.BoardRepository;
import com.java.web.solutionhub.board.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final BoardRepository boardRepository;
	
	/**
	 * Function to save review
	 * @param reviewDto
	 * @return
	 */
	@Transactional
	public Long saveReview(ReviewDto reviewDto) {
		var board = boardRepository.findById(reviewDto.getBoardId()).orElseThrow();
		
		var review = Review.builder()
				.userId(reviewDto.getUserId())
				.board(board)
				.totalReview(reviewDto.getTotalReview())
				.advantage(reviewDto.getAdvantage())
				.weakness(reviewDto.getWeakness())
				.point(reviewDto.getPoint())
				.build();
		
		log.info("Save Review By {}", reviewDto.getUserId());
		
		var getReview = reviewRepository.save(review);
		board.addReview(getReview);
		
		return getReview.getId();
	}
	
	/**
	 * Function to update review
	 * @param reviewDto
	 */
	@Transactional
	public void updateReview(ReviewDto reviewDto) {
		var getReview = reviewRepository.findById(reviewDto.getId()).orElseThrow();
		getReview.updateReview(reviewDto.getTotalReview(), reviewDto.getAdvantage(), reviewDto.getWeakness());
		log.info("update review by {}. update Review ID is {}", reviewDto.getUserId(), reviewDto.getId());
	}
	
	/**
	 * Function to delete review
	 * @param reviewDto
	 */
	public void deleteReview(ReviewDto reviewDto) {
		log.info("Delete Review review_id = {}, user_id = {}", reviewDto.getId(), reviewDto.getUserId());
		var board = boardRepository.getOne(reviewDto.getBoardId());
		var review = reviewRepository.getOne(reviewDto.getId());
		board.removeReview(review);
		reviewRepository.delete(review);
	}
	
	/**
	 * Function to get review information
	 * @param board
	 */
	public ReviewListInfo getReviewListByBoard(Long boardId) {
		var board = boardRepository.getOne(boardId);
		List<Review> reviewList = reviewRepository.findByBoard(board);
		List<ReviewDto> reviewDtoList = new ArrayList<>();
		
		for(Review review : reviewList) {
			reviewDtoList.add(ReviewDto.builder()
					.userId(review.getUserId())
					.advantage(review.getAdvantage())
					.boardId(review.getBoard().getId())
					.weakness(review.getWeakness())
					.time(review.getTime())
					.point(review.getPoint())
					.totalReview(review.getTotalReview())
					.id(review.getId())
					.build());
		}
		
		return ReviewListInfo.builder()
				.avgPoint(reviewRepository.getAvgPointByBoard(board))
				.reviewDtoList(reviewDtoList)
				.build();
		
	}
	
	/**
	 * Function to get review information by id
	 * @param reviewId
	 * @return
	 */
	public ReviewDto getReviewByReviewId(Long reviewId) {
		var review = reviewRepository.findById(reviewId).orElseThrow();
		
		return ReviewDto.builder()
				.id(review.getId())
				.userId(review.getUserId())
				.boardId(review.getBoard().getId())
				.totalReview(review.getTotalReview())
				.weakness(review.getWeakness())
				.advantage(review.getAdvantage())
				.point(review.getPoint())
				.time(review.getTime())
				.build();
	}
	
}
