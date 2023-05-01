package com.blogapi.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter


@Table(name="posts")
public class Post {
	
@Id @GeneratedValue(strategy=GenerationType.AUTO)
private Integer postId;

@Column(name="title",nullable=false)
private String postTitle;

@Column(name="content",nullable=false)
private String postContent;

@Column(name="date")
private String datePostAdded;

@Column(name="image")
private String postImageName;

@ManyToOne
private User user;
@ManyToOne
private Category category;

@OneToMany(mappedBy="post")
private Set<Comment> comment = new HashSet<>();

}
