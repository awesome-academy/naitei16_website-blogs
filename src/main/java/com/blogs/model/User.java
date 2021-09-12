package com.blogs.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.blogs.validator.UniqueEmail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	public User(String username, String name, Integer sex) {
		super();
		this.username = username;
		this.name = name;
		this.sex = sex;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Temporal(TemporalType.DATE) 
	@Column
	private Date dateOfBirth;
	
	@Column(name = "sex")
	private Integer sex;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleEnum role = RoleEnum.USER;
	
	@Column(name = "email", nullable = false, unique = true)
	@UniqueEmail(message = "{user.email.is.exist}")
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Transient
	private String retypePassword;
	
	@Column(name="isConfirmed")
	private boolean isConfirmed = false;
	
	// 1 user many post 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Post> listPost = new  ArrayList<Post>();
	
	// 1 user follow many other_user
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "relationship",
		joinColumns = @JoinColumn(name = "follower_id", referencedColumnName="id"),
		inverseJoinColumns = @JoinColumn(name = "followed_id")
	)
	private List<User> followeds; //people i follow 
	
	// many_user follow 1 user
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "followeds")
	private List<User> followers; // people follow me
	
	// 1 user like many post
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "like_users")
	private List<Post> like_posts;
	
	// 1 user have many comment
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentator")
	private List<Comment> comments;
	
	// 1 user have many notification
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver")
	private List<Notification> notifications;
	
	// 1 verification token
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE})
	private VerificationToken token;
	
}
