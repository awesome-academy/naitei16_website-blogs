package com.blogs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Entity
@Table(name="users")
public class User {
	private Integer id;
	private String username;
	private String name;
	private Date dateOfBirth;
	private Integer sex;
	private RoleEnum role;

	public User() {
		super();
	}

	public User(String username, String name, Integer sex) {
		super();
		this.username = username;
		this.name = name;
		this.sex = sex;
	}
	
	@Temporal(TemporalType.DATE) 
	@NotNull 
	@Column
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "username", length = 50)
	@NotNull
	public String getUsername() {
		return username;
	}
	
	@Column(name = "name", length = 50)
	@NotNull
	public String getName() {
		return name;
	}
	
	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	public RoleEnum getRole() {
		return role;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setRole(RoleEnum role) {
		this.role = role;
	}

}

