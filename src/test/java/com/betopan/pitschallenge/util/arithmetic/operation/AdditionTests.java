package com.betopan.pitschallenge.util.arithmetic.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;

public class AdditionTests {

	@Test
	void GivenAnAddition__WhenPassing_5_And_5__ThenItShouldBe10() {
    ArithmeticOperation operation = new Addition(5, 5);
    assertThat(operation.execute()).isEqualTo(10);
	}

	@Test
	void GivenAnAddition__WhenPassing_Minus5_And_Minus5__ThenItShouldBeMinus10() {
    ArithmeticOperation operation = new Addition(-5, -5);
    assertThat(operation.execute()).isEqualTo(-10);
	}

}
