package com.java.web.solutionhub.config.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Component;

import com.java.web.solutionhub.member.service.MemberService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	private String secreateKey = "jwtseedkey";
	private long tokenValidTime = 30 * 60 * 1000L;
	private final MemberService memberService;
	
	
	// Object INIT, secreateKey Encode base64
	@PostConstruct
	protected void init() {
		secreateKey = Base64.getEncoder().encodeToString(secreateKey.getBytes());
	}
	
	// JWT token create
	public String createToken(String userPk, List<String> roles) {
		var claims = Jwts.claims().setSubject(userPk);
		claims.put("roles", roles);
		var now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + tokenValidTime))
				.signWith(SignatureAlgorithm.HS256, secreateKey)
				.compact();
	}
}
