package com.akshat.bog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshat.bog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	 Optional<User>  findByEmail(String email);
}
