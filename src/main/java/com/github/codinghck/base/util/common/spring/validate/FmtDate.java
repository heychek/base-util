package com.github.codinghck.base.util.common.spring.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author hck 2019-04-08 18:55
 */
@Documented
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {FmtDateValidator.class})
public @interface FmtDate {

  String message() default "日期格式不正确";
  String fmt() default "yyyy-MM-dd hh:mm:ss";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  //指定多个时使用
  @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    FmtDate[] value();
  }
}