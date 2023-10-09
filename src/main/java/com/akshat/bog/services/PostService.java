package com.akshat.bog.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.akshat.bog.entities.Post;
import com.akshat.bog.payloads.PostDto;
import com.akshat.bog.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer catergoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePOst(Integer postId);

	PostResponse getAllPost(Integer PageNumber , Integer pageSize,String sortBy);
	
	PostDto getPostById(Integer postId);

	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get all post bu user
	List<PostDto> getPostByUSer(Integer userId);

	List<PostDto> searchPost(String keyword);

}
