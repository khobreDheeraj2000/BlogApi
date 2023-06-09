package com.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapi.dto.UserDto;
import com.blogapi.entity.User;
import com.blogapi.repository.UserRepository;
import com.blogapi.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		User savedUser = userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer user_id) {
		User user = userRepository.findById(user_id).orElseThrow();
		 user.setName(userDto.getName());
		 user.setEmail(userDto.getEmail());
		 user.setAbout(userDto.getAbout());
		 user.setPassword(userDto.getPassword());
		 
		 User updatedUser = this.userRepository.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow();
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> usersDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow();
		this.userRepository.delete(user);
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setAbout(user.getAbout());
		userDto.setPassword(user.getPassword());
		
		return userDto;
	}

	public User dtoToUser(UserDto userDto) {
		User user= new User();
		
		 user.setId(userDto.getId());
		 user.setName(userDto.getName());
		 user.setEmail(userDto.getEmail());
		 user.setAbout(userDto.getAbout());
		 user.setPassword(userDto.getPassword());
		
		return user;
	}

}





































