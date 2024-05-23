package com.betopan.pitschallenge.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private List<String> manuallyExpiredTokens = new ArrayList<>();


  public void expireToken(String jws) {
    this.manuallyExpiredTokens.add(jws);
  }

  public boolean isTokenManuallyExpired(String jws) {
    return this.manuallyExpiredTokens.contains(jws);
  }

}
