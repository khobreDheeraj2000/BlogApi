package com.blogapi.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapi.dto.PostDto;
import com.blogapi.dto.PostResponse;
import com.blogapi.entity.Category;
import com.blogapi.entity.Post;
import com.blogapi.entity.User;
import com.blogapi.repository.CategoryRepository;
import com.blogapi.repository.PostRepository;
import com.blogapi.repository.UserRepository;
import com.blogapi.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;
	@Autowired 
	ModelMapper modelMapper;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy,String sortDirection) {
		Sort sort = null ;
		 sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable page = PageRequest.of(pageNumber, pageSize, sort);
			Page<Post> pagePost = this.postRepository.findAll(page);
			List<Post> postsList = pagePost.getContent();
			List<PostDto> postsDto =  postsList.stream().map(post-> this.modelMapper.map(post, PostDto.class)).toList();
			PostResponse postResponse = new PostResponse();
			postResponse.setContent(postsDto);
			postResponse.setPageNumber(pagePost.getNumber());
			postResponse.setPageSize(pagePost.getSize());
			postResponse.setTotalElements(pagePost.getTotalElements());
			postResponse.setTotalPages(pagePost.getTotalPages());
			postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPost(int postId) {
			Post post = this.postRepository.findById(postId).orElseThrow();
		return this.modelMapper.map(post,PostDto.class) ;
	}

	@Override
	public PostDto createPost(PostDto postDto,int userId, int categoryId) {
			Post post = this.modelMapper.map(postDto, Post.class);
			//post.setDatePostAdded(new Date());
			Category category = this.categoryRepository.findById(categoryId).orElseThrow();
			post.setCategory(category);
			User user = this.userRepository.findById(userId).orElseThrow();
			post.setUser(user);
			Post savedPost = this.postRepository.save(post);
			return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow();
		post.setPostContent(postDto.getPostContent());
		post.setPostTitle(postDto.getPostTitle());
		post.setPostImageName(postDto.getPostImageName());
		Post updatedPost = postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow();
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDto> getPostsByUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow();
		List<Post> posts = this.postRepository.getByUser(user);
		List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).toList();
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId){
		Category category = this.categoryRepository.findById(categoryId).orElseThrow();
		List<Post> posts = this.postRepository.getByCategory(category);
		List<PostDto> postDtos =  posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).toList();
		return postDtos;
	}

	@Override
	public List<PostDto> getByPostTitleContaining(String key) {
		List<Post> posts = this.postRepository.getByPostTitleContaining(key);
		List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).toList();
		return postDtos;
	}

}
