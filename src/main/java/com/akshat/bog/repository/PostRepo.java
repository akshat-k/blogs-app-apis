package com.akshat.bog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshat.bog.entities.Category;
import com.akshat.bog.entities.Post;
import com.akshat.bog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
