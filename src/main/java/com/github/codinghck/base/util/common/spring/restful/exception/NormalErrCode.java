package com.github.codinghck.base.util.common.spring.restful.exception;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
@SuppressWarnings("unused")
public enum NormalErrCode implements ExceptionData {

  /**
   * 请求返回码枚举类, 详情参考 msg 字段说明
   */
  SUCCESS(200, "成功", "success"),
  PARAMETER_ERROR(400, "参数错误", "fail"),
  ACCESS_DENIED(403, "访问权限受限", "fail"),
  DATABASE_ERROR(5000, "数据库错误", "fail"),
  SIGN_KEY_ERROR(5001, "签名错误", "fail"),
  UNEXPECTED_ERROR(6000, "未知错误", "fail");

  private int code;
  private String msg;
  private String tip;

  NormalErrCode(int code, String msg, String tip) {
    this.code = code;
    this.msg = msg;
    this.tip = tip;
  }

  public int getCode() {
    return this.code;
  }

  public String getTip() {
    return this.tip;
  }

  public String getMsg() {
    return this.msg;
  }

  public static NormalErrCode find(int code) {
    NormalErrCode[] var1 = values();
    for (NormalErrCode cmd : var1) {
      if (cmd.getCode() == code) {
        return cmd;
      }
    }
    return UNEXPECTED_ERROR;
  }

  @Override
  public int getErrCode() {
    return this.code;
  }

  @Override
  public String getErrMsg() {
    return this.msg;
  }}

