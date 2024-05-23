package com.betopan.pitschallenge.operation;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betopan.pitschallenge.operation.dto.OperationExecutionRequestBody;
import com.betopan.pitschallenge.operation.enums.OperationType;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.record.Record;
import com.betopan.pitschallenge.record.RecordRepository;
import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;
import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperationFactory;
import com.betopan.pitschallenge.util.exception.InsufficientBalanceException;
import com.betopan.pitschallenge.util.random.RandomStringService;

@Service
public class OperationService {

  @Autowired private RandomStringService randomStringService;
  @Autowired private ArithmeticOperationFactory arithmeticOperationFactory;
  @Autowired private RecordRepository recordRepository;

  public void validateUserHasEnoughBalance(User user, Operation operation)
  {
    if (!user.hasEnoughBalanceToExecute(operation)) {
      throw new InsufficientBalanceException(
        String.format(
          "Your balance is %.2f and the %s operation cost is %.2f",
          user.getBalance(),
          operation.getType(),
          operation.getCost()
        )
      );
    }
  }

  public String executeOperation(OperationExecutionRequestBody body) {
    if (body.getOperationType().equalsIgnoreCase(OperationType.RANDOM_STRING.name())) {
      return this.randomStringService.generate();
    }
    ArithmeticOperation operation = this.arithmeticOperationFactory.get(body);
    return String.valueOf(operation.execute());
  }

  public void createRecord(User user, Operation operation, String operationResult) {
    // this.recordRepository.save(new Record(
    //   this.recordRepository.findAll().size() + 1,
    //   operation.getId(),
    //   user.getId(),
    //   operation.getCost(),
    //   user.getBalance(),
    //   operationResult,
    //   Instant.now().toEpochMilli()
    // ));
  }

}
