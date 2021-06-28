package com.java.web.solutionhub.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.web.solutionhub.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
