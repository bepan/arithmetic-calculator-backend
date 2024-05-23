package com.betopan.pitschallenge.auth.exceptions;

public class InvalidCredentialsException extends RuntimeException {

  private static String message = "Invalid username or password.";

  public InvalidCredentialsException() {
    super(InvalidCredentialsException.message);
  }

}
