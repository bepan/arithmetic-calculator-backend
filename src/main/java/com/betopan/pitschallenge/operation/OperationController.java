package com.betopan.pitschallenge.operation;

import java.util.Arrays;

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
import com.betopan.pitschallenge.record.Record;
import com.betopan.pitschallenge.record.RecordRepository;
import com.betopan.pitschallenge.user.User;
import com.betopan.pitschallenge.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operations")
public class OperationController {

  @Autowired private UserRepository userRepository;
  @Autowired private OperationRepository operationRepository;
  @Autowired private OperationService operationService;
  @Autowired private RecordRepository recordRepository;

	@PostMapping("/execute")
  @ResponseStatus(HttpStatus.OK)
	public @ResponseBody Greeting<String> execute(
    @Valid @RequestBody OperationExecutionRequestBody requestBody,
    HttpServletRequest req
  ) {
    String authUsername = (String)req.getAttribute("authUsername");
    User authenticatedUser = this.userRepository.findByUsername(authUsername);
    Operation requestedOperation = this.operationRepository.findByType(requestBody.getOperationType());

    this.operationService.validateUserHasEnoughBalance(authenticatedUser, requestedOperation);

    String operationResult = this.operationService.executeOperation(requestBody);

    Record record = new Record(authenticatedUser, requestedOperation, operationResult);
    this.recordRepository.save(record);

    authenticatedUser.decrementBalanceBy(requestedOperation.getCost());
    this.userRepository.save(authenticatedUser);

    return new Greeting<String>(operationResult);
  }
  

}
