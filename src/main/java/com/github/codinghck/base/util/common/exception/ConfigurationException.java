package com.github.codinghck.base.util.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * 2018/11/13 3:29 PM
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfigurationException extends Exception {

  private static final long serialVersionUID = -5043000680954387926L;

  public ConfigurationException(String message) {
    super(message);
  }
}
