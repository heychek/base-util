package com.github.codinghck.base.util.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * 2018/11/1 4:08 PM
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class ParamException extends RuntimeException {

  private static final long serialVersionUID = -5043000680954387925L;

  public ParamException(String message) {
    super(message);
  }
}
