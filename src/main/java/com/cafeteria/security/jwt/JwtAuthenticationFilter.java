package com.cafeteria.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cafeteria.role.entity.UserEntity;
import com.cafeteria.user.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtServie;
	private final UserRepository userRepository;
	
	public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
		
		this.jwtServie = jwtService;
		this.userRepository =userRepository;
		
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
		throws ServletException, IOException{
		
		final String authHeader = request.getHeader("Authorization");
		
		System.out.println("=== JWT FILTER ===");

		System.out.println("AUTH HEADER: " + authHeader);
		
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterchain.doFilter(request, response);
			
			return;
		}
		String jwt = authHeader.substring(7);
		System.out.println("JWT: " + jwt);
		try {
		String username = jwtServie.extractUsername(jwt);
		System.out.println("USERNAME: " + username);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserEntity user = userRepository.findByUsername(username).orElse(null);
			
		if(user !=null && jwtServie.isTokenValid(jwt, username)) {
			

		    System.out.println("TOKEN VALIDO");
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, null);
			
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		
		}
		System.out.println("TOKEN VALIDO");
		filterchain.doFilter(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
