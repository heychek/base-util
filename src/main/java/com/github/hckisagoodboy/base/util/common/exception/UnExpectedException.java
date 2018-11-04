package com.github.hckisagoodboy.base.util.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * @date 2018/11/2 4:38 PM
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UnExpectedException extends Exception {
  public UnExpectedException(String message) {
    super(message);
  }
}
