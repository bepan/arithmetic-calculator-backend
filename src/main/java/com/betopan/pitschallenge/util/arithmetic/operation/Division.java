package com.betopan.pitschallenge.util.arithmetic.operation;

import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;
import com.betopan.pitschallenge.util.arithmetic.TwoFactorArithmeticOperation;
import com.betopan.pitschallenge.util.exception.DividedByZeroException;

public class Division extends TwoFactorArithmeticOperation implements ArithmeticOperation {

  public Division(double number1, double number2) {
    super(number1, number2);
  }

  @Override
  public double execute() {    
    if (Math.abs(this.number1 / this.number2) == Double.POSITIVE_INFINITY) {
      throw new DividedByZeroException(
        String.format("You are trying to divide %.2f by %.2f", this.number1, this.number2)
      );
    }

    return this.number1 / this.number2;
  }

}
