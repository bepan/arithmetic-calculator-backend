package com.betopan.pitschallenge.util.arithmetic.operation;

import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;

public class SquareRoot implements ArithmeticOperation {

  private double number;

  public SquareRoot(double number) {
    this.number = number;
  }

  @Override
  public double execute() {
    return Math.pow(this.number, 2);
  }

}
