package com.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import com.blogapi.dto.UserDto;
import com.blogapi.payload.JwtAuthRequest;
import com.blogapi.payload.JwtAuthResponse;
import com.blogapi.security.JwtTokenHelper;
import com.blogapi.services.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserService userService;

	private static final Logger logger = Logger.getLogger(AuthController.class.getName());

//	@GetMapping("/test")
//	public ResponseEntity<String> demo() {
//		String str = "work testing";
//		return new ResponseEntity<>(str, HttpStatus.OK);
//	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception {
		logger.info("mapping is working...");
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,
				password);
		try {
			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {

			throw e ;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDto1 = this.userService.registerUser(userDto);
		return new ResponseEntity<>(userDto1,HttpStatus.OK);
	}
}
