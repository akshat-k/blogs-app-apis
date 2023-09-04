package com.akshat.bog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshat.bog.payloads.UserDto;
import com.akshat.bog.security.CustomUserDetailService;
import com.akshat.bog.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

	@Autowired
	private UserServices userServices;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	// POST create User

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
		UserDto createUSerDto = this.userServices.createUSer(userDto);

		return new ResponseEntity<>(createUSerDto, HttpStatus.CREATED);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto,
			@PathVariable("userId") Integer uid) {

		UserDto userById = this.userServices.getUserById(uid);
		if (userById != null) {
			UserDto updatedUser = this.userServices.update(userDto, uid);
			return ResponseEntity.ok(updatedUser);

		} else {
			System.out.println("Else condition");
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}

	}
 
	// Admin should only delete it
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer uid) {

		this.userServices.deleteUSer(uid);

		return new ResponseEntity<>("Customer with id " + uid + " deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userServices.getUserById(userId));
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userServices.getAllUsers());
	}

}
