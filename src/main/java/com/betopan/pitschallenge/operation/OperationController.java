package com.betopan.pitschallenge.operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.betopan.pitschallenge.operation.dto.Greeting;
import com.betopan.pitschallenge.operation.dto.OperationExecutionRequestBody;
import com.betopan.pitschallenge.operation.enums.OperationType;
import com.betopan.pitschallenge.record.Record;
import com.betopan.pitschallenge.record.RecordRepository;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;
import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;
import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperationFactory;
import com.betopan.pitschallenge.util.exception.InsufficientBalanceException;
import com.betopan.pitschallenge.util.random.RandomStringService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operations")
public class OperationController {

  @Autowired private UserRepository userRepository;
  @Autowired private OperationRepository operationRepository;
  @Autowired private RandomStringService randomStringService;
  @Autowired private ArithmeticOperationFactory arithmeticOperationFactory;
  @Autowired private RecordRepository recordRepository;

	@PostMapping("/execute")
  @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Object execute(
    @Valid @RequestBody OperationExecutionRequestBody requestBody,
    HttpServletRequest req
  ) {
    String operationResult = "";
    String authUsername = (String)req.getAttribute("authUsername");
    User authenticatedUser = this.userRepository.findByUsername(authUsername);
    Operation requestedOperation = this.operationRepository.findByType(requestBody.getOperationType());

    if (!authenticatedUser.hasEnoughBalanceToExecute(requestedOperation)) {
      throw new InsufficientBalanceException(authenticatedUser, requestedOperation);
    }

    if (requestBody.getOperationType().equalsIgnoreCase(OperationType.RANDOM_STRING.name())) {
      operationResult = this.randomStringService.generate();
    } else {
      ArithmeticOperation operation = this.arithmeticOperationFactory.get(requestBody);
      operationResult = String.valueOf(operation.execute());
    }
    String finalOperationResult = operationResult;

    Record record = new Record(authenticatedUser, requestedOperation, operationResult);
    this.recordRepository.save(record);

    authenticatedUser.decrementBalanceBy(requestedOperation.getCost());
    this.userRepository.save(authenticatedUser);

    return new Object() {
      public String result = finalOperationResult;
    };
  }
  

}
