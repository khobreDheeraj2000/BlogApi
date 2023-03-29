package com.blogapi.payload;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JwtAuthRequest {
	private String username;
	private String password;
	@Override
	public String toString() {
		return "JwtAuthRequest [username=" + username + ", password=" + password + "]";
	}
	
}
