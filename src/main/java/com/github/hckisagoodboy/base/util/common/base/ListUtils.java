package com.github.hckisagoodboy.base.util.common.base;

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

  /**
   * <p>判断传入的所有列表长度是否相等</p>
   *
   * @param lists 所有传入的 {@code List} 对象参数
   * @return 返回值: 需要注意的是, 如果传入的都为 {@code null} 值, 也会返回 {@code true}
   */
  public static boolean isLengthsSame(List... lists) {
    int standard = lists[0] == null ? -1 : lists[0].size();
    for (List list : lists) {
      boolean isNullInvalid = (list == null && standard != -1);
      boolean isLengthInvalid = (list != null && list.size() != standard);
      if (isNullInvalid || isLengthInvalid) {
        return false;
      }
    }
    return true;
  }
}
