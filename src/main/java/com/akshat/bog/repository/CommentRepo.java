package com.akshat.bog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshat.bog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
