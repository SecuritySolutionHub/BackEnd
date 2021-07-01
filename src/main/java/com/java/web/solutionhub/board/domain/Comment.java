package com.java.web.solutionhub.board.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "comment")
	private List<BoardComment> board;
	
	@Column(name="user_id")
	private Long userId;
	
	private String commentInfo;
	
	private Double point;
	
	
	@Column(name="time", nullable = false)
	private LocalDateTime time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Comment parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Comment> child = new ArrayList<>();
	
	public void addChildComment(Comment child) {
		this.child.add(child);
		child.setParent(this);
	}
	
	public void setParent(Comment parent) {
		this.parent = parent;
	}
	
	@Builder
	public Comment(Long userId, String commentInfo, Double point) {
		this.userId = userId;
		this.commentInfo = commentInfo;
		this.point = point;
		this.time = LocalDateTime.now();
	}
	
	public Long modifyComment(CommentDto commentDto) {
		this.commentInfo = commentDto.getCommentInfo();
		this.point = commentDto.getPoint();
		
		return commentDto.getId();
	}
}
