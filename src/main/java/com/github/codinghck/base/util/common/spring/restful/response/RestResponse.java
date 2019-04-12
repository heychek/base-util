package com.github.codinghck.base.util.common.spring.restful.response;

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
@SuppressWarnings("WeakerAccess")
public class RestResponse {

  private Integer code;
  private Object result;
  private String requestId;
  private String msg;
  private String tip;

  public RestResponse(Integer code, Object result, String msg, String tip) {
    this.code = code;
    this.result = result;
    this.msg = msg;
    this.tip = tip;
  }

  public RestResponse(Integer code, String msg, String tip) {
    this.code = code;
    this.msg = msg;
    this.tip = tip;
  }
}
