package com.akshat.bog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshat.bog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
	
}
