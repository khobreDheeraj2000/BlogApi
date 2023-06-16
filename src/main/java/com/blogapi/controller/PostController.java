package com.blogapi.controller;

import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.dto.ApiResponse;
import com.blogapi.dto.PostDto;
import com.blogapi.dto.PostResponse;
import com.blogapi.repository.PostRepository;
import com.blogapi.services.PostService;

@RestController
public class PostController {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostService postService;

	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/users/{userId}/categories/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userId,
			@PathVariable int categoryId) {
		PostDto postDto1 = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
	}

	@GetMapping("/categories/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryId) {
		List<PostDto> postDtos = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

	@GetMapping("/users/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userId) {
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection) {
		PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize,sortBy,sortDirection);
		System.out.print(postResponse + "postController Called !!");
		return new ResponseEntity<>(postResponse, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postId) {
		PostDto post = this.postService.getPost(postId);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@RequestParam(value="key", required=false) String key){
		List<PostDto> postDtos = this.postService.getByPostTitleContaining(key);
		return new ResponseEntity<>(postDtos,HttpStatus.OK);
		
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postId) {
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
	}

	@DeleteMapping("posts/{postId}")
	public ApiResponse deletePost(@PathVariable int postId) {
		this.postService.deletePost(postId);
		return new ApiResponse(200, "Deleted succesfully");
	}
}
