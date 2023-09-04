package com.akshat.bog.services;

import java.util.List;

import com.akshat.bog.payloads.UserDto;

public interface UserServices {

	UserDto registerNewUser(UserDto user);

	UserDto createUSer(UserDto user);

	UserDto update(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUSer(Integer userId);

}
