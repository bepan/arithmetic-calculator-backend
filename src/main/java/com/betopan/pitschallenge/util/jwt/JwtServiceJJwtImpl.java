package com.betopan.pitschallenge.util.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.betopan.pitschallenge.auth.exceptions.InvalidJWTException;

@Service
public class JwtServiceJJwtImpl implements JwtService {

  @Value("${security.jwt.secret-key}")
  private String secretKey;

  @Value("${security.jwt.expiration}") // 5 minutes
  private Long expirationInMillis;

  @Override
  public String generate(String subject) {
    return Jwts.builder()
      .subject(subject)
      .expiration(new Date(System.currentTimeMillis() + this.expirationInMillis))
      .signWith(this.getSignInKey())
      .compact();
  }

  @Override
  public String verifyAndGetSubject(String jws) {
    try {
      return Jwts.parser()
        .verifyWith(this.getSignInKey())
        .build()
        .parseSignedClaims(jws)
        .getPayload()
        .getSubject();
    }
    catch (JwtException e) {
      throw new InvalidJWTException();
    }
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
