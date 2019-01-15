package com.github.hckisagoodboy.base.util.common.lambda;

/**
 * @author hck
 * 2018/11/13 10:29 PM
 */
public interface CharExecutor<T> {
  /**
   * <p>用以 lambda 表达式的执行</p>
   * @param arg 传入参数
   * @return 返回结果
   */
  char execute(T arg);
}
