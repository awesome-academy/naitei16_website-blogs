package com.blogs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "commentator_id")
	private User commentator;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Comment parent_comment;
	
}
