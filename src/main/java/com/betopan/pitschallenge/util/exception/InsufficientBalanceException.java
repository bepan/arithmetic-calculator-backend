package com.betopan.pitschallenge.util.exception;

import com.betopan.pitschallenge.operation.Operation;
import com.betopan.pitschallenge.user.User;

public class InsufficientBalanceException extends RuntimeException {
  private static String message = "Your balance is %.2f and the %s operation cost is %.2f";

  public InsufficientBalanceException(User user, Operation operation) {
    super(
      String.format(
        InsufficientBalanceException.message,
        user.getBalance(),
        operation.getType(),
        operation.getCost()
      )
    );
  }

}
