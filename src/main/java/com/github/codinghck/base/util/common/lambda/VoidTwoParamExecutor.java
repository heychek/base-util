package com.github.codinghck.base.util.common.lambda;

/**
 * @author hck
 * 2018/11/18 4:21 PM
 */
public interface VoidTwoParamExecutor<T, K> {

  /**
   * <p>用以 lambda 表达式的执行</p>
   * @param t 传入参数
   * @param k 传入参数
   */
  void execute(T t, K k);
}
