package com.betopan.pitschallenge.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class LoginRequestDTO {

  @NotNull(message = "is required")
  @Email(message = "must be a correct email format")
  private String username;

  @NotNull(message = "is required")
  private String password;

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
