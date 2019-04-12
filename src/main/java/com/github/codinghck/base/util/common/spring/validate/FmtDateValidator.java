package com.github.codinghck.base.util.common.spring.validate;

import com.github.codinghck.base.util.common.base.date.DateFmtUtils;
import java.text.ParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hck 2019-04-08 18:55
 */
public class FmtDateValidator implements ConstraintValidator<FmtDate, String> {

  private String fmt;

  @Override
  public void initialize(FmtDate fmtDate) {
    fmt = fmtDate.fmt();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (s == null) {
      return true;
    }
    try {
      DateFmtUtils.strToDate(s, fmt);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }
}
