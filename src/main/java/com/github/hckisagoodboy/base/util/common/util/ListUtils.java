package com.github.hckisagoodboy.base.util.common.util;

import java.util.List;

/**
 * @author hck
 * 2018/11/3 11:11 PM
 */
public class ListUtils {
  private ListUtils() {}

  /**
   * <p>判断传进来的 {@code list} 是否为 null 并且不为空列表</p>
   *
   * @param list 需要判断的 <code>List</code> 对象
   * @return 判断是否为 null 或为空列表的布尔值
   */
  public static boolean isEmpty(List list) {
    return ObjUtils.isNull(list) || list.isEmpty();
  }
}
