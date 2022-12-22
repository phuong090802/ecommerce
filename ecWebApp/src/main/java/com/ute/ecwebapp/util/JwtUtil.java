package com.ute.ecwebapp.util;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ute.ecwebapp.dto.JwtDto;
import com.ute.ecwebapp.entity.AccountEntity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.issuer}")
	private String jwtIssuer;

	public String generateAccessToken(AccountEntity accountEntity) {
		return Jwts.builder().setSubject(accountEntity.getUserName()).setIssuer(jwtIssuer)
				.claim("ROLE", accountEntity.getRole().getRoleName()).claim("SUBJECT_ID", accountEntity.getAccountId())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException exception) {
			log.error("Invalid JWT token - {}", exception.getMessage());
		} catch (ExpiredJwtException exception) {
			log.error("Expired JWT token - {}", exception.getMessage());
		} catch (UnsupportedJwtException exception) {
			log.error("Unsupported JWT token - {}", exception.getMessage());
		} catch (IllegalArgumentException exception) {
			log.error("JWT claims string is empty - {}", exception.getMessage());
		}
		return false;
	}

	public JwtDto getJwtDto(String token) {
		var claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return JwtDto.builder().subject(claims.getSubject()).expirationDate(claims.getExpiration())
				.role(claims.get("ROLE", String.class)).build();
	}
}
