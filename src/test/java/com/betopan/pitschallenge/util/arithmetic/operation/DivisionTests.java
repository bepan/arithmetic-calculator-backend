package com.betopan.pitschallenge.util.arithmetic.operation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.betopan.pitschallenge.util.arithmetic.ArithmeticOperation;
import com.betopan.pitschallenge.util.exception.DividedByZeroException;

public class DivisionTests {

	@Test
	void GivenADivision__WhenDividing_5_By_2__ThenItShouldBe_2Point5() {
    ArithmeticOperation operation = new Division(5, 2);
    assertThat(operation.execute()).isEqualTo(2.5);
	}

	@Test
	void GivenADivision__WhenDividing_Minus5_By_2__ThenItShouldBe_Minus2Point5() {
    ArithmeticOperation operation = new Division(-5, 2);
    assertThat(operation.execute()).isEqualTo(-2.5);
	}

	@Test
	void GivenADivision__WhenDividing_5_By_Zero__ThenDividedByZeroExceptionMustBeThrown() {
    ArithmeticOperation operation = new Division(5, 0);
    assertThrows(DividedByZeroException.class, () -> {
      operation.execute();
    });
	}

}
