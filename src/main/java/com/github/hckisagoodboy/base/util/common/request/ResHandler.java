package com.github.hckisagoodboy.base.util.common.request;

/**
 * @author hck
 * @date 2018/10/29 3:46 PM
 */
public class ResHandler<T> {
  private static final String SUCCESS_MSG = "success";

  public ReqRes<T> getDefaultRqRes(T t) {
    ReqRes<T> result = new ReqRes<>();
    result.setResult(t);
    return result;
  }

  public ReqRes<String> getSuccessRqRes() {
    ReqRes<String> result = new ReqRes<>();
    result.setResult(SUCCESS_MSG);
    return result;
  }

  public ReqRes<T> getSuccessRqRes(T t) {
    ReqRes<T> result = new ReqRes<>();
    result.setCode(ErrCode.SUCCESS.getCode());
    result.setResult(t);
    return result;
  }

  public ReqRes<T> getByParamErr(String msg) {
    ReqRes<T> result = new ReqRes<>(ErrCode.PARAMETER_ERROR.getCode());
    result.setMsg(msg);
    return result;
  }

  public ReqRes<T> getByParamErr(String msg, String tip) {
    ReqRes<T> result = new ReqRes<>(ErrCode.PARAMETER_ERROR.getCode());
    result.setMsg(msg);
    result.setTip(tip);
    return result;
  }

  public ReqRes<T> getByParamErr(Throwable e) {
    ReqRes<T> result = new ReqRes<>(ErrCode.PARAMETER_ERROR.getCode());
    result.setMsg(e.getMessage());
    return result;
  }

  public ReqRes<T> getByParamErr(Throwable e, String tip) {
    ReqRes<T> result = new ReqRes<>(ErrCode.PARAMETER_ERROR.getCode());
    result.setMsg(e.getMessage());
    result.setTip(tip);
    return result;
  }

  public ReqRes<T> getRqResByCode(ErrCode code, String msg) {
    ReqRes<T> result = new ReqRes<>(code.getCode());
    result.setMsg(msg);
    return result;
  }

  public ReqRes<T> getRqResByCode(ErrCode code, T t) {
    ReqRes<T> result = new ReqRes<>(code.getCode());
    result.setResult(t);
    return result;
  }

  public ReqRes<T> getRqResByCode(ErrCode code, T t, String tip) {
    ReqRes<T> result = new ReqRes<>(code.getCode());
    result.setResult(t);
    result.setTip(tip);
    return result;
  }
}
