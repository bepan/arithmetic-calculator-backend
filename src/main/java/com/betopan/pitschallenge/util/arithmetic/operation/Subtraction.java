package com.betopan.pitschallenge.util.arithmetic.operation;

import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;
import com.betopan.pitschallenge.util.arithmetic.TwoFactorArithmeticOperation;

public class Subtraction extends TwoFactorArithmeticOperation implements ArithmeticOperation {

  public Subtraction(double number1, double number2) {
    super(number1, number2);
  }

  @Override
  public double execute() {
    return this.number1 - this.number2;
  }

}
