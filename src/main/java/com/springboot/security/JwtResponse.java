package com.springboot.security;

import com.springboot.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
	private User user;
	private String jwtToken;
}
 