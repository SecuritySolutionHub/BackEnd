package com.java.web.solutionhub.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "point")
	private Long point;
	
	@Column(name = "userId")
	private Long userId;
	
	@Column(name = "title")
	private String totalReview;
	
	@Column(name = "advantage")
	private String advantage;
	
	@Column(name = "weakness")
	private String weakness;
	
	@Column(name = "time")
	private LocalDateTime time;

	@JoinColumn(name = "board_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
	
	@Builder
	public Review(Board board, String totalReview, String advantage, String weakness, Long point, Long userId) {
		this.board = board;
		this.totalReview = totalReview;
		this.advantage = advantage;
		this.weakness = weakness;
		this.userId = userId;
		this.point = point;
		this.time = LocalDateTime.now();
	}
}