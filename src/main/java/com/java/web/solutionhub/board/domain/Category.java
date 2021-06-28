package com.java.web.solutionhub.board.domain;

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
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "category")
	private List<BoardCategory> board;
	
	@Column(name = "category_type")
	private String categoryType;
	
	@JoinColumn(name = "parent_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> child;
	
	public void addChildCategory(Category childCategory) {
		childCategory.setParent(this);
		this.child.add(childCategory);
	}
	
	public void subChildCategory(Category child) {
		this.child.remove(child);
	}
	
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	@Builder
	public Category(String categoryType) {
		this.categoryType = categoryType;
	}
	
}
