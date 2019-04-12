package com.github.codinghck.base.util.common.spring.validate;

import com.github.codinghck.base.util.common.base.str.StrBoolUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-08 18:56
 */
public class StrBoolValidator implements ConstraintValidator<StrBool, String> {

  @Override
  public void initialize(StrBool strBool) {

  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    return StrBoolUtils.isBool(s);
  }
}
