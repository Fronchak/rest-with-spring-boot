package com.fronchak.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fronchak.data.vo.v1.security.TokenVO;
import com.fronchak.exceptions.InvalidJwtAuthenticationException;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";
	
	@Value("${security.jwt.token.expire-lenght:3600000}")
	private long validityInMilliseconds = 3600000;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	Algorithm algorithm = null;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
		algorithm = Algorithm.HMAC256(secretKey.getBytes());
	}
	
	public TokenVO createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String accessToken = getAccessToken(username, roles, now, validity);
		String refreshToken = getRefreshToken(username, roles, now);
		TokenVO token = new TokenVO();
		token.setUsername(username);
		token.setAuthenticated(true);
		token.setCreated(now);
		token.setExpiration(validity);
		token.setAccessToken(accessToken);
		token.setRefreshToken(refreshToken);
		return token;
	}
	
	private String getAccessToken(String username, List<String> roles, Date now, Date validity) {
		String issuerUrl = ServletUriComponentsBuilder
				.fromCurrentContextPath().build().toString();
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withSubject(username)
				.withIssuer(issuerUrl)
				.sign(algorithm)
				.strip();
				
	}
	
	public String getRefreshToken(String username, List<String> roles, Date now) {
		Date validityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
		return JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validityRefreshToken)
				.withSubject(username)
				.sign(algorithm)
				.strip();
	}
	
	public Authentication getAuthentication(String token) {
		DecodedJWT decodedJWT = decodedToken(token);
		UserDetails userDetails = userDetailService
				.loadUserByUsername(decodedJWT.getSubject());
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
		
	}
	
	private DecodedJWT decodedToken(String token) {
		Algorithm algo = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(algo).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT;
	}
	
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring("Bearer ".length());
		}
		return null;
	}
	
	public boolean validateToken(String token) {
		DecodedJWT decodedJWt = decodedToken(token);
		try {
			if(decodedJWt.getExpiresAt().before(new Date())) return false;
			return true;
		}
		catch(Exception e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
		}
	}
	
}
