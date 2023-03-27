package com.blogapi.services;

import java.util.List;

import com.blogapi.dto.PostDto;
import com.blogapi.dto.PostResponse;

public interface PostService {
	
	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy,String sortDirection);
	PostDto getPost(int postId);
	PostDto createPost(PostDto postDto , int userId, int categoryId);
	PostDto updatePost(PostDto postDto,int postId);
	void deletePost(int postId);
	List<PostDto> getByPostTitleContaining(String key);
	List<PostDto> getPostsByUser(int userId); 
	List<PostDto> getPostsByCategory(int categoryId);
	
	
}
