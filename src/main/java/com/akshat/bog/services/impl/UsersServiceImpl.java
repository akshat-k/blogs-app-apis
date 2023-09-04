package com.akshat.bog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akshat.bog.config.AppConstants;
import com.akshat.bog.entities.Role;
import com.akshat.bog.entities.User;
import com.akshat.bog.exceptions.ResourceNotFoundException;
import com.akshat.bog.payloads.UserDto;
import com.akshat.bog.repository.RoleRepo;
import com.akshat.bog.repository.UserRepo;
import com.akshat.bog.services.UserServices;

@Service
public class UsersServiceImpl implements UserServices {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	// Create User
	@Override
	public UserDto createUSer(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);

		return this.userToDto(savedUser);
	}

	// Update User
	@Override
	public UserDto update(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).orElseThrow((() -> new ResourceNotFoundException("User", "id", userId)));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepo.save(user);
		UserDto userDto2 = this.userToDto(updateUser);

		return userDto2;
	}

	// Get User By Id

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	// Get All User

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub

		List<User> users = this.userRepo.findAll();

		List<UserDto> collectUsers = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return collectUsers;
	}

	// Delete User

	@Override
	public void deleteUSer(Integer userId) {
		// TODO Auto-generated method stub

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		this.userRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto) {
		User user = this.mapper.map(userDto, User.class);
		/*
		 * user.setId(userDto.getId()); user.setName(userDto.getName());
		 * user.setEmail(userDto.getEmail()); user.setAbout(userDto.getAbout());
		 * user.setPassword(userDto.getPassword());
		 */
		return user;

	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.mapper.map(user, UserDto.class);
		/*
		 * userDto.setId(user.getId()); userDto.setName(user.getName());
		 * userDto.setEmail(user.getEmail()); userDto.setAbout(user.getAbout());
		 * userDto.setPassword(user.getPassword());
		 */
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub

		User registerUser = this.mapper.map(userDto, User.class);
		registerUser.setPassword(this.passwordEncoder.encode(registerUser.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		registerUser.getRoles().add(role);
		User newRegisterUser = this.userRepo.save(registerUser);
		return this.mapper.map(newRegisterUser, UserDto.class);
	}

}
