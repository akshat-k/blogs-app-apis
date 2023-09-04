package com.akshat.bog.services;

import java.util.List;

import com.akshat.bog.payloads.CategoryDto;


public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	public void deleteategory(Integer categoryId);

	public CategoryDto getCategory(Integer categoryId);

	public List<CategoryDto> getCategories();

}
