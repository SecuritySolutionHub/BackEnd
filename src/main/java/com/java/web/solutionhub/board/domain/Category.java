package com.java.web.solutionhub.board.domain;

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
import javax.persistence.OneToOne;

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
public class Category {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "category")
	private List<BoardCategory> board;
	
	@Column(name = "category_type")
	private String categoryType;
	
	@JoinColumn(name = "parent_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Category parent;
	
	@OneToOne(mappedBy = "parent")
	private Category child;
	
	public void addChildCategory(Category child) {
		child.setParent(this);
		this.child = child;
	}
	
	public void subChildCategory(Category child) {
		this.child = null;
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	@Builder
	public Category(String categoryType) {
		this.categoryType = categoryType;
	}
	
}
