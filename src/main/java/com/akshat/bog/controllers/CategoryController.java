package com.akshat.bog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.bog.payloads.ApiResponse;
import com.akshat.bog.payloads.CategoryDto;
import com.akshat.bog.services.CategoryService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// Create category

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createUser(@RequestBody @Valid CategoryDto categoryDto) {
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
	}

	// Update Category

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateUser(@RequestBody @Valid CategoryDto categoryDto,
			@PathVariable("categoryId") Integer catId) {

		CategoryDto userExists = this.categoryService.getCategory(catId);
		if (userExists != null) {
			CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
			return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
		} else {

			return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
		}

	}

	// Delete category based on ID

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer catId) {
		this.categoryService.deleteategory(catId);
		return new ResponseEntity<ApiResponse>(
				(HttpStatusCode) new ApiResponse("Category Deleted sicessfully", true, HttpStatus.OK));
	}

	// Getting category by Id

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer catId) {

		CategoryDto getCategoryById = this.categoryService.getCategory(catId);
		if (getCategoryById != null) {
			return new ResponseEntity<>(getCategoryById, HttpStatus.OK);
		} else {
			return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
		}

	}

	// Getting all Categories

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getALLCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}

}
