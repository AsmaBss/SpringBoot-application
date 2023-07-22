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
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

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
			authorities.add(new SimpleGrantedAuthority(role.getType().name()));
		});
		return authorities;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			UserDetails userDetails = loadUserByUsername(username);
	        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				System.out.println("Mot de passe incorrecte");
	            throw new BadCredentialsException("Mot de passe incorrecte");
	        }
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Utilisateur non autorisé", e);
		} catch (BadCredentialsException e) {
			System.out.println("Mot de passe incorrecte");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Mot de passe incorrecte", e);
		} catch (UsernameNotFoundException e) {
			System.out.println("Email introuvable");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email introuvable", e);
		}
	}

}
