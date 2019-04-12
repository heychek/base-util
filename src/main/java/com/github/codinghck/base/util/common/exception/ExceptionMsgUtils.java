package com.github.codinghck.base.util.common.exception;

/**
 * @author hck 2019-04-10 22:13
 */
public class ExceptionMsgUtils {

  private ExceptionMsgUtils() {}

  /**
   * <P>获取异常的堆栈信息</P>
   *
   * @param e 异常对象
   * @return 堆栈信息字符串
   */
  public static String getTraceInfo(Exception e) {
    StringBuilder sb = new StringBuilder();
    StackTraceElement[] traces = e.getStackTrace();
    for (StackTraceElement trace : traces) {
      sb.append(trace).append(" ");
    }
    return sb.toString();
  }
}
