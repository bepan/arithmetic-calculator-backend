package com.betopan.pitschallenge.auth.exceptions;

public class InvalidJWTException extends RuntimeException{
  private static String message = "Invalid token sent, either because it was expired or corrupted";

  public InvalidJWTException() {
    super(InvalidJWTException.message);
  }
}
