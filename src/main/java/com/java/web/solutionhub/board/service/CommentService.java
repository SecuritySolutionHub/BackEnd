package com.java.web.solutionhub.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.BoardComment;
import com.java.web.solutionhub.board.domain.Comment;
import com.java.web.solutionhub.board.domain.CommentDto;
import com.java.web.solutionhub.board.repository.BoardCommentRepository;
import com.java.web.solutionhub.board.repository.BoardRepository;
import com.java.web.solutionhub.board.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
	
	
	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final BoardCommentRepository boardCommentRepository;
	
	
	/**
	 * save comment function
	 * @param commentDto
	 * @return
	 */
	@Transactional
	public Long saveComment(CommentDto commentDto, Long boardId) {
		var comment = Comment.builder()
				.userId(commentDto.getUserId())
				.point(commentDto.getPoint())
				.commentInfo(commentDto.getCommentInfo())
				.build();
		var saveComment = commentRepository.save(comment);
		var board =	boardRepository.findById(boardId).orElseThrow();
		
		var boardComment = BoardComment.builder()
							.board(board)
							.comment(saveComment)
							.build();
		
		boardCommentRepository.save(boardComment);
		log.info("add Comment {} by {}", comment.getCommentInfo(), comment.getUserId());
		return commentRepository.save(comment).getId();
		
	}
	
	/**
	 * update comment function
	 * @param commentDto
	 * @return
	 */
	@Transactional
	public Long updateComment(CommentDto commentDto) {
		var comment = commentRepository.findById(commentDto.getId()).orElseThrow();
		return comment.modifyComment(commentDto);
	}
	
	/**
	 * delete comment function
	 * @param commentDto
	 */
	@Transactional
	public void deleteComment(CommentDto commentDto) {
		var comment = commentRepository.findById(commentDto.getId()).orElseThrow();
		commentRepository.delete(comment);
	}
	
	/**
	 * add childComment function
	 * @param commentDto
	 * @param parentId
	 */
	@Transactional
	public void addChildComment(CommentDto commentDto, Long parentId) {
		var parentComment = commentRepository.findById(parentId).orElseThrow();
	}
	
	/**
	 * to get CommentList function by boardId
	 * @param BoardId
	 * @return
	 */
	public List<CommentDto> getCommentListByBoardId(Long boardId) {
		
		var board = boardRepository.findById(boardId).orElseThrow();
		List<BoardComment> categoryList = boardCommentRepository.findByBoard(board);
		List<CommentDto> resultList = new ArrayList<>();
		
		for(BoardComment boardComment : categoryList) {
			var comment = boardComment.getComment();
			var commentDto = CommentDto.builder()
					.id(comment.getId())
					.userId(comment.getUserId())
					.commentInfo(comment.getCommentInfo())
					.point(comment.getPoint())
					.build();
			
			resultList.add(commentDto);
		}
		
		return resultList;
	}

}
