package com.java.web.solutionhub;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.java.web.solutionhub.board.domain.BoardDto;
import com.java.web.solutionhub.board.service.BoardService;
import com.java.web.solutionhub.board.service.CategoryService;
import com.java.web.solutionhub.member.domain.enum_package.MemberStatic.memberRoll;
import com.java.web.solutionhub.member.dto.MemberSaveRequsetDto;
import com.java.web.solutionhub.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InitDataBase {

	private final MemberService memberService;
	private final BoardService boardService;
	private final CategoryService categoryService;
	
	
	@PostConstruct
	@Transactional
	public void init() {
		MemberSaveRequsetDto firstMember = MemberSaveRequsetDto.builder()
    			.userId("first_test")
    			.password("testPassword")
    			.companyEmail("first@naver.com")
    			.roll(memberRoll.USER)
    			.build();
		
		MemberSaveRequsetDto secondMember = MemberSaveRequsetDto.builder()
    			.userId("second_test")
    			.password("testPassword")
    			.companyEmail("second@naver.com")
    			.roll(memberRoll.USER)
    			.build();
		
		memberService.join(firstMember);
		memberService.join(secondMember);
		
		
		BoardDto firstPost = BoardDto.builder()
				.userId("first_test")
				.title("First title")
				.content("first content is")
				.build();
		
		BoardDto secondPost = BoardDto.builder()
				.userId("second_test")
				.title("Second title")
				.content("second content is")
				.build();
		
		
		Long boardId = boardService.uploadPost(firstPost);
		boardService.uploadPost(secondPost);
		
		var category = "TEST CATEGORY";
		Long cateId = categoryService.addCategory(category);
		
		var categoryChild = "CHILD CATEGORY";
		Long childCateid = categoryService.addCategory(categoryChild);
		
		categoryService.addChildCategoryToBoard(boardId, cateId, childCateid);
		
		boardService.addBoardCategory(boardId, cateId);
	}
	
}
