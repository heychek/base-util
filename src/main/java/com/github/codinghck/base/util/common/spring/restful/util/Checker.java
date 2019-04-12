package com.github.codinghck.base.util.common.spring.restful.util;

import com.github.codinghck.base.util.common.spring.restful.exception.ExceptionData;
import com.github.codinghck.base.util.common.spring.restful.exception.RestException;
import java.util.function.Predicate;

/**
 * @author hck 2019-04-10 17:38
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Checker {

  private Checker() {}

  /**
   * <p>如果校验成功就抛出异常</p>
   *
   * @param eData 异常信息
   * @param pre 校验方法
   * @param obj 需校验参数
   * @param <T> 参数类型
   */
  public static <T> void throwIfLegal(T obj, ExceptionData eData, Predicate<T> pre) {
    if (!pre.test(obj)) {
      throw new RestException(eData);
    }
  }

  /**
   * <p>如果校验不成功就抛出异常</p>
   *
   * @param eData 异常信息
   * @param pre 校验方法
   * @param obj 需校验参数
   * @param <T> 参数类型
   */
  public static <T> void throwIfIllegal(T obj, ExceptionData eData, Predicate<T> pre) {
    if (pre.test(obj)) {
      throw new RestException(eData);
    }
  }

  /**
   * <p>如果校验成功就抛出异常</p>
   *
   * @param eData 异常信息
   * @param pre 校验方法
   * @param objs 需校验参数列表
   * @param <T> 参数类型
   */
  public static <T> void throwIfLegal(ExceptionData eData, Predicate<T> pre, T[] objs) {
    for (T obj : objs) {
      throwIfLegal(obj, eData, pre);
    }
  }

  /**
   * <p>如果校验不成功就抛出异常</p>
   *
   * @param eData 异常信息
   * @param pre 校验方法
   * @param objs 需校验参数列表
   * @param <T> 参数类型
   */
  public static <T> void throwIfIllegal(ExceptionData eData, Predicate<T> pre, T[] objs) {
    for (T obj : objs) {
      throwIfIllegal(obj, eData, pre);
    }
  }
}
