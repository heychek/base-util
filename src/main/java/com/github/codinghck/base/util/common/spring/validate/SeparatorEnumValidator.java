package com.github.codinghck.base.util.common.spring.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-12 16:12
 */
public class SeparatorEnumValidator implements ConstraintValidator<SeparatorEnum, String> {

  private String[] enumElements;
  private String separator;

  @Override
  public void initialize(SeparatorEnum separatorEnum) {
    enumElements = separatorEnum.enumElements();
    separator = separatorEnum.separator();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (s == null || enumElements == null || enumElements.length <= 0) {
      return true;
    }
    String[] elements = s.split(separator);
    for (String element : elements) {
      if (!isElementValid(element)) {
        return false;
      }
    }
    return true;
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
