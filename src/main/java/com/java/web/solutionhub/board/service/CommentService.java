package com.java.web.solutionhub.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Comment;
import com.java.web.solutionhub.board.domain.CommentDto;
import com.java.web.solutionhub.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	
	
	private final CommentRepository commentRepository;
	
	/**
	 * save comment function
	 * @param commentDto
	 * @return
	 */
	public Long saveComment(CommentDto commentDto) {
		var comment = Comment.builder()
				.userId(commentDto.getUserId())
				.point(commentDto.getPoint())
				.commentInfo(commentDto.getCommentInfo())
				.build();
		
		log.info("add Comment {} by {}", comment.getCommentInfo(), comment.getUserId());
		return commentRepository.save(comment).getId();
		
	}
	
	/**
	 * update comment function
	 * @param commentDto
	 * @return
	 */
	public Long updateComment(CommentDto commentDto) {
		var comment = commentRepository.findById(commentDto.getId()).orElseThrow();
		return comment.modifyComment(commentDto);
	}
	
	/**
	 * delete comment function
	 * @param commentDto
	 */
	public void deleteComment(CommentDto commentDto) {
		var comment = commentRepository.findById(commentDto.getId()).orElseThrow();
		commentRepository.delete(comment);
	}

}
