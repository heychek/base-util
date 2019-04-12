package com.github.codinghck.base.util.common.spring.restful.exception;

/**
 * @author hck 2019-04-10 22:25
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class RestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private int errCode = NormalErrCode.UNEXPECTED_ERROR.getCode();

  public RestException() {
    super();
  }

  public RestException(String message) {
    super(message);
  }

  public RestException(ExceptionData exceptionData) {
    this(exceptionData.getErrCode(), exceptionData.getErrMsg());
  }

  public RestException(ExceptionData exceptionData, Throwable cause) {
    this(exceptionData.getErrCode(), exceptionData.getErrMsg(), cause);
  }

  public RestException(int errCode, String message) {
    super(message);
    this.errCode = errCode;
  }

  public RestException(int errCode, String message, Throwable cause) {
    super(message, cause);
    this.errCode = errCode;
  }

  public int getErrCode() {
    return errCode;
  }
}