package com.github.codinghck.base.util.common.spring.restful.util;

import com.github.codinghck.base.util.common.spring.restful.exception.NormalErrCode;
import com.github.codinghck.base.util.common.spring.restful.response.RestResponse;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
@SuppressWarnings("unused")
public class RestResHandler {

  public static RestResponse getSuccess() {
    NormalErrCode c = NormalErrCode.SUCCESS;
    return new RestResponse(c.getCode(), c.getMsg(), c.getTip());
  }

  public static RestResponse getSuccess(Object o) {
    NormalErrCode c = NormalErrCode.SUCCESS;
    return new RestResponse(c.getCode(), o, c.getMsg(), c.getTip());
  }

  public static RestResponse getSuccess(Object o, String msg) {
    NormalErrCode c = NormalErrCode.SUCCESS;
    return new RestResponse(c.getCode(), o, msg, c.getTip());
  }

  public static RestResponse getForParamErr() {
    NormalErrCode c = NormalErrCode.PARAMETER_ERROR;
    return new RestResponse(c.getCode(), c.getMsg(), c.getTip());
  }

  public static RestResponse getForParamErr(String msg) {
    NormalErrCode c = NormalErrCode.PARAMETER_ERROR;
    return new RestResponse(c.getCode(), msg, c.getTip());
  }

  public static RestResponse getForParamErr(Throwable e) {
    NormalErrCode c = NormalErrCode.PARAMETER_ERROR;
    return new RestResponse(c.getCode(), e.getMessage(), c.getTip());
  }

  public static RestResponse getForAccessErr() {
    NormalErrCode c = NormalErrCode.ACCESS_DENIED;
    return new RestResponse(c.getCode(), c.getMsg(), c.getTip());
  }

  public static RestResponse getForAccessErr(String msg) {
    NormalErrCode c = NormalErrCode.ACCESS_DENIED;
    return new RestResponse(c.getCode(), msg, c.getTip());
  }

  public static RestResponse getForAccessErr(Throwable e) {
    NormalErrCode c = NormalErrCode.ACCESS_DENIED;
    return new RestResponse(c.getCode(), e.getMessage(), c.getTip());
  }

  public static RestResponse getForUnexpectErr() {
    NormalErrCode c = NormalErrCode.UNEXPECTED_ERROR;
    return new RestResponse(c.getCode(), c.getMsg(), c.getTip());
  }

  public static RestResponse getForUnexpectErr(String msg) {
    NormalErrCode c = NormalErrCode.UNEXPECTED_ERROR;
    return new RestResponse(c.getCode(), msg, c.getTip());
  }

  public static RestResponse getForUnexpectErr(Throwable e) {
    NormalErrCode c = NormalErrCode.UNEXPECTED_ERROR;
    return new RestResponse(c.getCode(), e.getMessage(), c.getTip());
  }

  public static RestResponse getByCode(NormalErrCode c) {
    return new RestResponse(c.getCode(), c.getMsg(), c.getTip());
  }

  public static RestResponse getByCode(NormalErrCode c, String msg) {
    return new RestResponse(c.getCode(), msg, c.getTip());
  }

  public static RestResponse getByCode(NormalErrCode c, Object o) {
    return new RestResponse(c.getCode(), o, c.getMsg(), c.getTip());
  }

  public static RestResponse getByCode(NormalErrCode c, Object o, String msg) {
    return new RestResponse(c.getCode(), o, msg, c.getTip());
  }
}
