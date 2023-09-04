package com.akshat.bog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.bog.entities.Comment;
import com.akshat.bog.payloads.ApiResponse;
import com.akshat.bog.payloads.CommentDto;
import com.akshat.bog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId) {

		CommentDto createComment = this.commentService.createComment(comment, postId);
		System.out.println(createComment.toString());
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.OK);
	}

	@DeleteMapping("/comment/{commentId}")
	public ApiResponse deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteComment(commentId);

		/*
		 * return new ResponseEntity<ApiResponse>( (HttpStatusCode) new
		 * ApiResponse("Comment Deleted", true, HttpStatus.OK));
		 */
		
		return new ApiResponse("Comment Deleted", true, HttpStatus.OK);
	}

}
