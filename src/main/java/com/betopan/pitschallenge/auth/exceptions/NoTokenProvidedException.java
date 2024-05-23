package com.betopan.pitschallenge.auth.exceptions;

public class NoTokenProvidedException extends RuntimeException {
  private static String message = "No authorization header provided.";

  public NoTokenProvidedException() {
    super(NoTokenProvidedException.message);
  }
}
