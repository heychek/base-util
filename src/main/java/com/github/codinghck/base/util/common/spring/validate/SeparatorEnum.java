package com.github.codinghck.base.util.common.spring.validate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.github.codinghck.base.util.common.base.str.StrConst;
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
@Constraint(validatedBy = {SeparatorEnumValidator.class})
public @interface SeparatorEnum {
  String message() default "字符串列表不正确";
  String[] enumElements();
  String separator() default StrConst.DOT_SEPARATOR;

  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

  //指定多个时使用
  @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
  @Retention(RUNTIME)
  @Documented
  @interface List {
    SeparatorEnum[] value();
  }
}
