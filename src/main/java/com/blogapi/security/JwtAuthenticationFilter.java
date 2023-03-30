package com.blogapi.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blogapi.controller.AuthController;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	 private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

	@Autowired
	JwtTokenHelper jwtTokenHelper;
	@Autowired
	UserDetailsService userDetailsService ;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestToken = request.getHeader("Authorization");
		//Bearer 2356hjvjhv
		
		String userName = null;
		String token = null; 
		//logger.info("hello ");
		if(requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try{userName = this.jwtTokenHelper.getUsernameFromToken(token);}
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt token");
				throw e ;
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
				throw e ;
			} catch (MalformedJwtException e) {
				throw e ;
			}
		}
			else{
			 System.out.println("Jwt Token does not start with bearer");
		}
		
		//once we get the token , now we validate 
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null ) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				// shi chal rha h
				// authentication krna hai
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Invalid jwt Token");
			}
		} else {
			System.out.println("username is null or context is not null");
		}
		filterChain.doFilter(request,response);
}
}
