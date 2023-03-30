package com.blogapi.services;

import java.util.List;

import com.blogapi.dto.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer user_id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	UserDto registerUser(UserDto userDto);
	
}
