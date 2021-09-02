package com.blogs.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private Integer numberOfViews = 0;
	
	@Column(nullable = false)
	private StatusEnum status = StatusEnum.NOT_APPROVED;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;
	
	@ManyToMany
	@JoinTable(name = "post_category",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;
	
	@ManyToMany
	@JoinTable(name = "userLikePost",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> like_users;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Content> contents;
	
}
