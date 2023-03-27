package com.blogapi.services;

import com.blogapi.dto.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto,int postId);
	void deleteComment(int commentId);
}
