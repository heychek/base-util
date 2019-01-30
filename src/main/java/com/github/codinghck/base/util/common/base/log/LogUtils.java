package com.github.codinghck.base.util.common.base.log;

import com.alibaba.fastjson.JSONObject;
import com.github.codinghck.base.util.common.base.date.DateFmtUtils;
import com.github.codinghck.base.util.common.base.str.StrConst;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;

/**
 * @author hck
 * 2018/10/29 12:11 PM
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class LogUtils {

  private LogUtils() {}

  /**
   * <p>根据传入的日志对象和异常对象, 打印该异常的信息</p>
   *
   * @param log 日志对象
   * @param e 异常对象
   */
  public static void logThrowable(Logger log, Throwable e) {
    logThrowable(log, e, StrConst.EMPTY_STRING);
  }

  /**
   * <p>根据传入的日志对象和异常对象以及错误提示, 打印该异常的信息</p>
   *
   * @param log 日志对象
   * @param e 异常对象
   * @param tip 错误提示
   */
  public static void logThrowable(Logger log, Throwable e, String tip) {
    log.error("发生异常, tip = {}, e = {}, msg = {}, stackTrace = {}",
        tip, e, e.getMessage(), e.getStackTrace());
  }

  /**
   * <p>根据传入的日志对象和切点 {@link JoinPoint} 对象, 打印该切点需要的日志</p>
   * @param log 日志对象
   * @param jp 切点对象
   */
  public static void aspectLog(Logger log, JoinPoint jp) {
    aspectLogWithRes(log, jp, null);
  }

  /**
   * <p>根据传入的日志对象和切点 {@link JoinPoint} 对象以及该切点的执行结果, 打印该切点需要的日志</p>
   * @param log 日志对象
   * @param jp 切点对象
   * @param proceedRes 该切点的执行结果
   */
  public static void aspectLogWithRes(Logger log, JoinPoint jp, Object proceedRes) {
    String className = jp.getTarget().getClass().getName();
    String methodName = jp.getSignature().getName();
    String paramNames = Arrays.toString(((CodeSignature) jp.getSignature()).getParameterNames());
    String paramArgs = Arrays.toString(jp.getArgs());
    String returnValue = JSONObject.toJSONString(proceedRes);
    String time = DateFmtUtils.currTimeStrDefaultPattern();
    log.info("className = {},  methodName = {}, paramNames = {}, "
            + "paramArgs = {}, returnValue = {}, time = {}",
        className, methodName, paramNames, paramArgs, returnValue, time);
  }
}
