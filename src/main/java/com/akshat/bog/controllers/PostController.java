package com.akshat.bog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akshat.bog.config.AppConstants;
import com.akshat.bog.payloads.ApiResponse;
import com.akshat.bog.payloads.PostDto;
import com.akshat.bog.payloads.PostResponse;
import com.akshat.bog.services.FileService;
import com.akshat.bog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// Creating a post

	@PostMapping("/user/{userID}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userID,
			@PathVariable Integer categoryId) {

		PostDto createPost = this.postService.createPost(postDto, userID, categoryId);

		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get by user

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {

		List<PostDto> postByUSer = this.postService.getPostByUSer(userId);

		return new ResponseEntity<List<PostDto>>(postByUSer, HttpStatus.OK);

	}

	// Get by category Id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostCategory(@PathVariable Integer categoryId) {

		List<PostDto> postByCat = this.postService.getPostByCategory(categoryId);

		return new ResponseEntity<List<PostDto>>(postByCat, HttpStatus.OK);

	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(

			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy) {
		System.out.println("Akshat------------------>>>");

		PostResponse allPostResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy);
		System.out.println("Akshat----------->");

		return new ResponseEntity<PostResponse>(allPostResponse, HttpStatus.OK);
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto postById = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);

	}

	// Delete Post

	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {

		this.postService.deletePOst(postId);

		return new ApiResponse("Post Deleted !!!", true, HttpStatus.OK);
	}

	// Update Post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// Search with any keyword
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> serachPostByTitle(@PathVariable("keyword") String keyword) {
		List<PostDto> searchPost = this.postService.searchPost(keyword);

		return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);
	}

	// Post image upload

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostDto postById = this.postService.getPostById(postId);
		String uploadImage = this.fileService.uploadImage(path, image);
		System.out.println(uploadImage);
		postById.setImageName(uploadImage);
		PostDto updatePost = this.postService.updatePost(postById, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
}
