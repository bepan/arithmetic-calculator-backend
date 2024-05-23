package com.betopan.pitschallenge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.betopan.pitschallenge.operation.OperationController;

@SpringBootTest
class PitsChallengeApplicationTests {

  @Autowired
  private OperationController operationController;

	@Test
	void contextLoads() {
    assertThat(10).isEqualTo(10);
	}

}
