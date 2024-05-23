package com.betopan.pitschallenge.util.exception.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.betopan.pitschallenge.auth.exceptions.InvalidCredentialsException;
import com.betopan.pitschallenge.auth.exceptions.InvalidJWTException;
import com.betopan.pitschallenge.auth.exceptions.NoTokenProvidedException;
import com.betopan.pitschallenge.util.exception.DividedByZeroException;
import com.betopan.pitschallenge.util.exception.InsufficientBalanceException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = DividedByZeroException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponseDTO handleDividedByZeroException(DividedByZeroException ex) 
  {
    return new ErrorResponseDTO(
      ex.getClass().getSimpleName(),
      ex.getMessage()
    );
  }

  @ExceptionHandler(value = InsufficientBalanceException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public @ResponseBody ErrorResponseDTO handleInsufficientBalanceException(InsufficientBalanceException ex) 
  {
    return new ErrorResponseDTO(
      ex.getClass().getSimpleName(),
      ex.getMessage()
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody Object handleMethodArgumentNotValid(MethodArgumentNotValidException ex) 
  {
    Map<String, List<String>> body = new HashMap<>();
    
    List<String> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(fieldError -> "The " + fieldError.getField() + " " + fieldError.getDefaultMessage())
      .collect(Collectors.toList());
    
      body.put("errors", errors);
      return body;
  }

  @ExceptionHandler({InvalidCredentialsException.class, NoTokenProvidedException.class, InvalidJWTException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public @ResponseBody Object handleUnauthorizedExceptions(RuntimeException ex) 
  {
    return new ErrorResponseDTO(
      ex.getClass().getSimpleName(),
      ex.getMessage()
    );
  }

  @ExceptionHandler({RuntimeException.class, Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody Object handleCatchAll(RuntimeException ex) 
  {
    return new ErrorResponseDTO(
      ex.getClass().getSimpleName(),
      ex.getMessage()
    );
  }

}
