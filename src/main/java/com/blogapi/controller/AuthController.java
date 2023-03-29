package com.blogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.blogapi.payload.JwtAuthRequest;
import com.blogapi.payload.JwtAuthResponse;
import com.blogapi.security.JwtTokenHelper;

@RestController
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService ;
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 private static final Logger logger = Logger.getLogger(AuthController.class.getName());
	 
	 @GetMapping("/login")
	 public ResponseEntity<String> demo(){
		 String str = "work testing";
		 return new ResponseEntity<>(str,HttpStatus.OK);
	 }
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		logger.info("Doing something...");
		System.out.println(request.toString());
		this.authenticate(request.getUsername(),request.getPassword());
		System.out.println(request.getUsername()  + "      & " + request.getPassword() );
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		System.out.println(userDetails.toString());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		System.out.println(token);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
		try{
			this.authenticationManager.authenticate(authenticationToken);
			System.out.println("This is authenticationToken : " + authenticationToken);
			}
		catch(BadCredentialsException e) {
			System.out.println("invalid details");
			throw new Exception("Invalid userName or password");
		}
	}
}
