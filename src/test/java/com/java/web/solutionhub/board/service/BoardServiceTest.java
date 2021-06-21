package com.java.web.solutionhub.board.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.repository.BoardRepository;


@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	BoardRepository BoardRepository;
	
	@Autowired
	EntityManager em;

	@Test
	void saveTest() {
		//given
		BoardDto inputData = BoardDto.builder()
				.userId("test User")
				.title("TEST 1")
				.content("TEST 1 Contents")
				.build();
		//when
		Long id = boardService.uploadPost(inputData);
		BoardDto getData = boardService.getBoardInfoByIdx(id);
		
		//then
		assertEquals(getData.getTitle(), inputData.getTitle());
	}
	
	@Test
	void searchTitleTest() {
		BoardDto inputData = BoardDto.builder()
				.userId("test User")
				.title("TEST 1")
				.content("TEST 1 Contents")
				.build();
		
		 boardService.uploadPost(inputData);
		 List<BoardDto> getData = boardService.getBoardInfoByUserId("test User");
		 
		 for(BoardDto data : getData) {
			 assertEquals(data.getTitle(), "TEST 1");
		 }
	}

}
