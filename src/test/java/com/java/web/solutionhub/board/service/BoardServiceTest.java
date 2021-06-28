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

import lombok.extern.slf4j.Slf4j;


@Slf4j
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
		
		
		Long fisrtBoardId = boardService.uploadPost(firstPost);
		boardService.uploadPost(secondPost);
		
		var category = "TEST CATEGORY";
		Long cateId = categoryService.addCategory(category);
		
		var categoryChild = "CHILD CATEGORY";
		Long childCateid = categoryService.addCategory(categoryChild);
		
		log.info("cateID is {}", cateId);
		log.info("child CateID is {}", childCateid);
		
		
		categoryService.addChildCategoryToBoard(fisrtBoardId, cateId, childCateid);
		
		log.info("TEST 2");
		boardService.addBoardCategory(fisrtBoardId, cateId);
		
		
		log.info("TEST 3");
		var getData = boardService.getBoardInfoByCategory(cateId);
		
		
		log.info("TEST 4");
		assertEquals(getData.get(0).getTitle(), firstPost.getTitle());
//		assertEquals(getCate.getChild(), categoryService.findCategoryById(childCateid));
	}

}
