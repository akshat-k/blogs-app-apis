package com.akshat.bog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.akshat.bog.entities.Category;
import com.akshat.bog.entities.Post;
import com.akshat.bog.entities.User;
import com.akshat.bog.exceptions.ResourceNotFoundException;
import com.akshat.bog.payloads.PostDto;
import com.akshat.bog.payloads.PostResponse;
import com.akshat.bog.repository.CategoryRepo;
import com.akshat.bog.repository.PostRepo;
import com.akshat.bog.repository.UserRepo;
import com.akshat.bog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catergoryId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userId));

		Category category = this.categoryRepo.findById(catergoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category ID", catergoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.jpg");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post); 
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override

	public void deletePOst(Integer postId) {
		// TODO Auto-generated method stub

		Post postById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

		this.postRepo.delete(postById);

	}

	// Get all the post

	@Override
	public PostResponse getAllPost(Integer PageNumber, Integer pageSize, String sortBy) {
		// TODO Auto-generated method stub

		// int pazeSize = 5;
		// int pageNumber = 1;

		org.springframework.data.domain.Pageable p = PageRequest.of(PageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> content = pagePost.getContent();
		List<PostDto> collectAllPost = content.stream().map((allpost) -> this.modelMapper.map(allpost, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();

		postResponse.setContent(collectAllPost);

		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setPageNumber(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		

		System.out.println(postResponse.toString());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post postById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

		return this.modelMapper.map(postById, PostDto.class);

	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub

		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUSer(Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

		List<Post> userPosts = this.postRepo.findByUser(user);

		List<PostDto> UserPosts = userPosts.stream().map((userpost) -> this.modelMapper.map(userpost, PostDto.class))
				.collect(Collectors.toList());

		return UserPosts;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub

		List<Post> findByTitleContaining = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = findByTitleContaining.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

}
