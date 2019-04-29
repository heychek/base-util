package com.github.codinghck.base.util.common.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-12 16:12
 */
public class NumExceptEnumValidator implements ConstraintValidator<NumExceptEnum, Long> {

  private long[] enumElements;

  @Override
  public void initialize(NumExceptEnum numExceptEnum) {
    enumElements = numExceptEnum.enumElements();
  }

  @Override
  public boolean isValid(Long l, ConstraintValidatorContext context) {
    if (l == null || enumElements == null || enumElements.length <= 0) {
      return true;
    }
    return isElementValid(l);
  }

  private boolean isElementValid(Long element) {
    for (long enumElement : enumElements) {
      if (element == enumElement) {
        return false;
      }
    }
    return true;
  }
}
