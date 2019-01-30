package com.github.codinghck.base.util.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * 2018/11/2 4:38 PM
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings("unused")
public class UnExpectedException extends Exception {

  private static final long serialVersionUID = -5043000680954387922L;

  public UnExpectedException(String message) {
    super(message);
  }
}
