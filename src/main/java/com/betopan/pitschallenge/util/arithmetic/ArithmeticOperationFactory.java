package com.betopan.pitschallenge.util.arithmetic;

import org.springframework.stereotype.Service;

import com.betopan.pitschallenge.operation.OperationType;
import com.betopan.pitschallenge.operation.dto.OperationExecutionRequestBody;
import com.betopan.pitschallenge.util.arithmetic.operation.Addition;
import com.betopan.pitschallenge.util.arithmetic.operation.Division;
import com.betopan.pitschallenge.util.arithmetic.operation.Multiplication;
import com.betopan.pitschallenge.util.arithmetic.operation.SquareRoot;
import com.betopan.pitschallenge.util.arithmetic.operation.Subtraction;

@Service
public class ArithmeticOperationFactory {

  public ArithmeticOperation get(OperationExecutionRequestBody body) {
    String operationType = body.getOperationType();
    double number1 = body.getNumber1().doubleValue();
    double number2 = body.getNumber2().doubleValue();

    if (operationType.equalsIgnoreCase(OperationType.ADDITION.name())) {
      return new Addition(number1, number2);
    }
    else if (operationType.equalsIgnoreCase(OperationType.SUBTRACTION.name())) {
      return new Subtraction(number1, number2);
    }
    else if (operationType.equalsIgnoreCase(OperationType.DIVISION.name())) {
      return new Division(number1, number2);
    }
    else if (operationType.equalsIgnoreCase(OperationType.SQUARE_ROOT.name())) {
      return new SquareRoot(number1);
    }
    else if (operationType.equalsIgnoreCase(OperationType.MULTIPLICATION.name())) {
      return new Multiplication(number1, number2);
    }
    else {
      throw new RuntimeException("Arithmetic Operation not supported.");
    }
  }

}
