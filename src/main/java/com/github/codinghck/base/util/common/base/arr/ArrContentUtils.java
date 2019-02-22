package com.github.codinghck.base.util.common.base.arr;

import com.github.codinghck.base.util.common.base.obj.ObjContentUtils;

/**
 * @author hck 2019-01-30 20:25
 */
@SuppressWarnings("unused")
public class ArrContentUtils {

  private ArrContentUtils() {}

  /**
   * <p>判断传进来的数组是否为 null 并且不为空列表</p>
   *
   * @param <T> 数组类型
   * @param arr 需要判断的数组对象
   * @return 判断是否为 null 或为空列表的布尔值
   */
  public static <T> boolean isArrEmpty(T[] arr) {
    return ObjContentUtils.isNull(arr) || arr.length <= 0;
  }
}
