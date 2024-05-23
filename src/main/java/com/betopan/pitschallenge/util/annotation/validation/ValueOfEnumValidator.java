package com.betopan.pitschallenge.util.annotation.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
  private List<String> acceptedValues;

  @Override
  public void initialize(ValueOfEnum annotation) {
    acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
      .map(Enum::name)
      .collect(Collectors.toList());
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) 
  {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
      "must be any of: " +
      String.join("|", acceptedValues.stream().map(String::toLowerCase).toList())
    )
    .addConstraintViolation();
    
    if (value == null) {
      return true;
    }

    return acceptedValues.stream().anyMatch(value.toString()::equalsIgnoreCase);
  }
  
}
