package com.github.hckisagoodboy.base.util.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalOut<T> {
  private String requestId;
  private T data;
}
