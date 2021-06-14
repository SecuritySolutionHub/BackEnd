package com.java.web.solutionhub.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	// TODO : BoardService implementation needs to be completed
	private final BoardRepository boardRepository;
	
	public List<BoardDto> searchPostByTitle(String title) {
		List<Board> boards = boardRepository.findByTitle(title);
		List<BoardDto> resultList = new ArrayList<>();
		
		if(boards.isEmpty())
			return resultList;
		
		for(Board board : boards) {
			resultList.add(this.convertEntityToDto(board));
		}
		
		return resultList;
	}
	
	
	private BoardDto convertEntityToDto(Board board) {
		return BoardDto.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.categoryId(board.getCategoryId())
				.commentId(board.getCommentId())
				.build();
	}
}
