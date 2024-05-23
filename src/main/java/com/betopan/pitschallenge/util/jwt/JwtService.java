package com.betopan.pitschallenge.util.jwt;

public interface JwtService {

  public String generate(String subject);
  public String verifyAndGetSubject(String jws);

}
