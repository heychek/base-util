package com.github.codinghck.base.util.common.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-12 16:12
 */
public class StrEnumValidator implements ConstraintValidator<StrEnum, String> {

  private String[] enumElements;

  @Override
  public void initialize(StrEnum strEnum) {
    enumElements = strEnum.enumElements();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (s == null || enumElements == null || enumElements.length <= 0) {
      return true;
    }
    return isElementValid(s);
  }

  private boolean isElementValid(String element) {
    for (String enumElement : enumElements) {
      if (enumElement.equals(element)) {
        return true;
      }
    }
    return false;
  }
}
