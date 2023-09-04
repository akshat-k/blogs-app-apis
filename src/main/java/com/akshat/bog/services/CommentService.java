package com.akshat.bog.services;

import com.akshat.bog.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto , Integer postId);
	
	void deleteComment(Integer commentId);
}
