package com.github.codinghck.base.util.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SuppressWarnings("WeakerAccess")
public class ReqRes<T> {
  @NonNull
  private Integer code;
  private String msg;
  private String tip;
  private T result;
}
