package com.github.codinghck.base.util.common.spring.restful.exception;

/**
 * @author hck 2019-04-10 17:37
 */
public interface ExceptionData {

  /**
   * <P>获取异常码</P>
   *
   * @return 异常码
   */
  int getErrCode();

  /**
   * <p>获取异常信息</p>
   *
   * @return 异常信息
   */
  String getErrMsg();
}