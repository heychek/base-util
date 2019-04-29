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
 * @author hck 2019-04-12 16:12
 */
@Documented
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {NumEnumValidator.class})
public @interface NumEnum {

  String message() default "数字不正确";
  long[] enumElements();

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  //指定多个时使用
  @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    NumEnum[] value();
  }
}
