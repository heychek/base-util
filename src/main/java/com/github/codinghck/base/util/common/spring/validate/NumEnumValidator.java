package com.github.codinghck.base.util.common.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-12 16:12
 */
public class NumEnumValidator implements ConstraintValidator<NumEnum, Long> {

  private long[] enumElements;

  @Override
  public void initialize(NumEnum numEnum) {
    enumElements = numEnum.enumElements();
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
        return true;
      }
    }
    return false;
  }
}
