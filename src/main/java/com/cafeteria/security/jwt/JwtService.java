package com.cafeteria.security.jwt;



import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${security.jwt.secret-key}")
	private String secretKey;
	
	private Key getSigningKey() {
		byte[] keyBytes = 
				secretKey.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String generateToken(String username) {
		
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(
						System.currentTimeMillis()+ 86400000))
				.signWith(getSigningKey())
				.compact();
	}
	
	public String extractUsername(String token) {
		
		return extractAllClaims(token)
                .getSubject();
	}
	
	public boolean isTokenValid(
			String token,
			String username) {
		
		String tokenUsername = extractUsername(token);
		
		return tokenUsername.equals(username) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		
		return extractAllClaims(token)
				.getExpiration()
				.before(new Date());
	}
	
	private Claims extractAllClaims(String token) {
		
		return Jwts.parser()
				.verifyWith((javax.crypto.SecretKey)
						getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
