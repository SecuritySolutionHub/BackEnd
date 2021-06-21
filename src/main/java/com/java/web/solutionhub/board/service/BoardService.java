package com.java.web.solutionhub.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
	// TODO : BoardService implementation needs to be completed
	private final BoardRepository boardRepository;
	
	public Long uploadPost(BoardDto boardDto){
		var result = boardRepository.save(boardDto.convertEntity());
		log.info("Post is upload. Title is {}", boardDto.getTitle());
		return result.getId();
	}
	
	public BoardDto getBoardInfoByIdx(Long idx) {
		var getData = boardRepository.getOne(idx);
		return BoardDto.builder()
				.title(getData.getTitle())
				.content(getData.getContent())
				.userId(getData.getUserId())
				.id(idx)
				.build();
	}
	
	public List<BoardDto> getBoardInfoByUserId(String userId) {
		List<Board> findResult = boardRepository.findByUserId(userId);
		return findResult.stream()
				.map(m -> new BoardDto(m.getId(), m.getUserId(), m.getTitle(), m.getContent()))
				.collect(Collectors.toList());
	}

}
