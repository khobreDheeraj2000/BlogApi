package com.blogapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogapi.dto.UserDto;
import com.blogapi.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/users")
	ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDto1 = userService.createUser(userDto);
		return new ResponseEntity<>(userDto1,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users= userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	ResponseEntity<UserDto> getUserById(@PathVariable("id")int id){
		UserDto userDto= userService.getUserById(id);
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
	
	@PutMapping("users/{id}")
	ResponseEntity<UserDto> upDateUser(@Valid @RequestBody UserDto userDto,@PathVariable("id") int id){
		UserDto updatedUserDto = userService.updateUser(userDto, id);
		return new ResponseEntity<>(updatedUserDto,HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("users/{id}")
	ResponseEntity<?> deleteUser(@PathVariable("id")int id){
		userService.deleteUser(id);
		return ResponseEntity.ok("deleted sucesfully");
	}
	
}
