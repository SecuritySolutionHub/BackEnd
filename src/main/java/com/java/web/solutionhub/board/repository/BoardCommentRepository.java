package com.java.web.solutionhub.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.Board;
import com.java.web.solutionhub.board.domain.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long>{

	public List<BoardComment> findByBoard(Board board);
}
