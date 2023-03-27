package com.blogapi.dto;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	private String postTitle;
	private String postContent;
	private String postImageName="default.png";
	private Date postDateAdded;
	private UserDto user;
	private CategoryDto category;
	private Set<CommentDto> comment = new HashSet<>();
}
