package com.betopan.pitschallenge.util.exception.handler;

public class ErrorResponseDTO {
  private String exceptionName;
  private String message;


  public ErrorResponseDTO(String exceptionName, String message) {
    this.exceptionName = exceptionName;
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  public String getExceptionName() {
    return this.exceptionName;
  }

  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }

}
