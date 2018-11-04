package com.github.hckisagoodboy.base.util.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * @date 2018/11/1 4:08 PM
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParamException extends RuntimeException {
  public ParamException(String message) {
    super(message);
  }
}
