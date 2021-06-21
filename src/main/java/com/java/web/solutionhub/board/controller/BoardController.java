package com.java.web.solutionhub.board.controller;

import org.springframework.web.bind.annotation.RestController;

import com.java.web.solutionhub.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
}
