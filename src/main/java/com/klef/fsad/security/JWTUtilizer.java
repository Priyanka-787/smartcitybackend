package com.klef.fsad.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTUtilizer {

	private final String SECRET_KEY_STRING = "c2VjdXJlX2p3dF9rZXlfZm9yX3NtYXJ0Y2l0eV9hcHBfMjAyNl9zYWZlX2tleQ==";

	private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_STRING));

	public String generateJWTToken(String email, String role) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("email", email);
		claims.put("role", role);

		return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // 2 hours
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	// 🔍 Validate Token
	public Map<String, String> validateToken(String token) {

		Map<String, String> response = new HashMap<>();

		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

			response.put("email", claims.get("email", String.class));
			response.put("role", claims.get("role", String.class));
			response.put("subject", claims.getSubject());
			response.put("code", "200");

		} catch (ExpiredJwtException e) {
			response.put("code", "401");
			response.put("error", "Token expired. Please login again");
		} catch (Exception e) {
			response.put("code", "403");
			response.put("error", "Invalid token");
		}

		return response;
	}
}