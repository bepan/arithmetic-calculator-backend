package com.betopan.pitschallenge.operation.dto;

import java.math.BigDecimal;

import com.betopan.pitschallenge.operation.enums.OperationType;
import com.betopan.pitschallenge.util.annotation.validation.ValueOfEnum;

import jakarta.validation.constraints.NotNull;

public class OperationExecutionRequestBody {

  @NotNull(message = "is required")
  @ValueOfEnum(enumClass = OperationType.class)
  private String operationType;
  
  private BigDecimal number1;

  private BigDecimal number2;

  public String getOperationType() {
    return this.operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public BigDecimal getNumber1() {
    return this.number1;
  }

  public void setNumber1(BigDecimal number1) {
    this.number1 = number1;
  }

  public BigDecimal getNumber2() {
    return this.number2;
  }

  public void setNumber2(BigDecimal number2) {
    this.number2 = number2;
  }

}
