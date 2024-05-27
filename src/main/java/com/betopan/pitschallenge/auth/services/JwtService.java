package com.betopan.pitschallenge.auth.services;

public interface JwtService {

  public String generate(String subject);
  public String verifyAndGetSubject(String jws);

}
