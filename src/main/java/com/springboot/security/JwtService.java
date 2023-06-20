package com.springboot.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.models.User;
import com.springboot.repositories.UserRepo;

import lombok.Setter;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String pasword = jwtRequest.getPassword();
		
			authenticate(username, pasword);
			final UserDetails userDetails = loadUserByUsername(username);
			String newGeneratedToken = jwtUtil.generateToken(userDetails);
			User user = userRepo.findByEmail(username);
			return new JwtResponse(user, newGeneratedToken);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username); 
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					getAuthorities(user));
		} else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}

	private Set getAuthorities(User user) {
		Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			System.out.println(role.getType().name());
			authorities.add(new SimpleGrantedAuthority(role.getType().name()));
		});
		return authorities;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credential from user");
		}
	}

}
