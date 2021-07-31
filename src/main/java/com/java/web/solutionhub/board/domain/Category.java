package com.java.web.solutionhub.board.domain;

import java.util.List;
import java.util.Optional;

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

	/**
	 * Parent의 값이 null인지 확인하며 반환하는 함수
	 * @return
	 */
	public Long getParentAfterNullCheck() {
		return Optional.ofNullable(this.parent)
				.map(Category::getParent)
				.map(Category::getId)
				.orElse((long) 0);
	}
	
	@Builder
	public Category(String categoryType) {
		this.categoryType = categoryType;
	}
	
}
