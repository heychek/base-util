package com.github.hckisagoodboy.base.util.common.request;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
public enum ErrCode {
  // 请求返回码枚举类
  SUCCESS(0, "成功", ""),
  PARAMETER_ERROR(400, "参数错误", ""),
  ACCESS_DENIED(403, "访问权限受限", ""),
  DATABASE_ERROR(5000, "数据库错误", ""),
  SIGN_KEY_ERROR(5001, "签名错误", ""),
  UNEXPECTED_ERROR(6000, "未知错误", "");

  private int code;
  private String msg;
  private String tip;

  ErrCode(int code, String msg, String tip) {
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

  public static ErrCode find(int code) {
    ErrCode[] var1 = values();
    for (ErrCode cmd : var1) {
      if (cmd.getCode() == code) {
        return cmd;
      }
    }
    return UNEXPECTED_ERROR;
  }
}

