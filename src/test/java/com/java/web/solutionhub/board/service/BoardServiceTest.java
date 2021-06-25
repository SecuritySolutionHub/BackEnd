package com.java.web.solutionhub.board.service;

import static org.junit.jupiter.api.Assertions.*;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.java.web.solutionhub.board.controller.BoardController;
import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.domain.Category;
import com.java.web.solutionhub.board.repository.BoardRepository;
import com.java.web.solutionhub.board.repository.CategoryRepository;


@Transactional
@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardServiceTest {
	
	@Autowired
	BoardService boardService;
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BoardRepository boardRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BoardController boardController;
	
	@Autowired
	EntityManager em;

//	@Test
//	void saveTest() {
//		//given
//		BoardDto inputData = BoardDto.builder()
//				.userId("test User")
//				.title("TEST 1")
//				.content("TEST 1 Contents")
//				.build();
//		//when
//		Long id = boardService.uploadPost(inputData);
//		BoardDto getData = boardService.getBoardInfoByIdx(id);
//		
//		//then
//		assertEquals(getData.getTitle(), inputData.getTitle());
//	}
//	
//	@Test
//	void searchTitleTest() {
//		BoardDto inputData = BoardDto.builder()
//				.userId("test User")
//				.title("TEST 1")
//				.content("TEST 1 Contents")
//				.build();
//		
//		 boardService.uploadPost(inputData);
//		 List<BoardDto> getData = boardService.getBoardInfoByUserId("test User");
//		 
//		 for(BoardDto data : getData) {
//			 assertEquals(data.getTitle(), "TEST 1");
//		 }
//	}
	
	@Test
	void addCategory() {
		BoardDto firstPost = BoardDto.builder()
				.userId("First_User")
				.title("First title")
				.content("first content is")
				.build();
		
		BoardDto secondPost = BoardDto.builder()
				.userId("Second_User")
				.title("Second title")
				.content("second content is")
				.build();
		
		
		Long boardId = boardService.uploadPost(firstPost);
		boardService.uploadPost(secondPost);
		
		var category = "TEST CATEGORY";
		Long cateId = categoryService.addCategory(category);
		
		var categoryChild = "CHILD CATEGORY";
		Long childCateid = categoryService.addCategory(categoryChild);
		
		System.out.println(cateId);
		System.out.println(childCateid);
		
		categoryService.addChildCategory(cateId, childCateid);
		
		boardService.addBoardCategory(boardId, cateId);
		
	}

}
