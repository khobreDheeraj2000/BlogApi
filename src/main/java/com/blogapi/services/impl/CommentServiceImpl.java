package com.blogapi.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.dto.CommentDto;
import com.blogapi.entity.Comment;
import com.blogapi.entity.Post;
import com.blogapi.repository.CommentRepository;
import com.blogapi.repository.PostRepository;
import com.blogapi.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentRepository commentRepository ;
	@Autowired
	PostRepository postRepository;
	@Autowired
	ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow();
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow();
		this.commentRepository.delete(comment);
	}

}
