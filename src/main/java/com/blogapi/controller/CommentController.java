package com.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.dto.ApiResponse;
import com.blogapi.dto.CommentDto;
import com.blogapi.services.CommentService;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService ;
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId) {
		CommentDto commentDto1 = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<>(commentDto1,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ApiResponse deleteComment(@PathVariable("commentId") int commentId) {
		commentService.deleteComment(commentId);
		return new ApiResponse(200, "comment deleted successfully");
		
	}
}
