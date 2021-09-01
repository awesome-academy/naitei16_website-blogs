package com.blogs.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
}
