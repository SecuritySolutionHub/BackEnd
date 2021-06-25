package com.java.web.solutionhub;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.service.BoardService;
import com.java.web.solutionhub.board.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDataBase {

	
	private final BoardService boardService;
	private final CategoryService categoryService;
	
	
	@PostConstruct
	@Transactional
	public void init() {
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
		
		categoryService.addChildCategory(cateId, childCateid);
		
		boardService.addBoardCategory(boardId, cateId);
	}
	
}
