package com.blogs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {
	private Integer follower_id;
	private Integer followed_id;
	private String type; //follow or unfollow
}