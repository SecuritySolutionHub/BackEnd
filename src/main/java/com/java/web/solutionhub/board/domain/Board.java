package com.java.web.solutionhub.board.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Board {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	
	@Column(name = "upload_date", nullable = false)
	private LocalDateTime uploadDate;
	
	@Column(name = "category_id")
	@OneToMany(mappedBy = "board")
	private List<BoardCategory> boardCategory = new ArrayList<>();
	
	@Column(name = "comment_id")
	@OneToMany(mappedBy = "comment")
	private List<BoardComment> boardComment = new ArrayList<>();
	
	@Column(name = "review_id")
	@OneToMany(mappedBy = "id")
	private List<Review> reviewList = new ArrayList<>();
	
	public void modifyContent(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	@Builder
	public Board(String title, String content, String userId) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.uploadDate = LocalDateTime.now();
	}
	
	public void addBoardCategory(BoardCategory category) {
		boardCategory.add(category);
	}
	
	public void subBoardCategory(BoardCategory category) {
		boardCategory.remove(category);
	}
	
	public void addReview(Review review) {
		this.reviewList.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviewList.remove(review);
	}
}
