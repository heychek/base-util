package com.github.codinghck.base.util.common.base.collection;

import com.github.codinghck.base.util.common.base.obj.ObjContentUtils;
import java.util.Collection;

/**
 * @author hck 2019-01-30 20:29
 */
@SuppressWarnings("unused")
public class CollectionLenUtils {

  private CollectionLenUtils() {}

  private static final int INVALID_LEN = -1;

  /**
   * <p>判断传入的所有列表长度是否相等</p>
   *
   * @param collections 所有传入的对象参数
   * @return 返回值: 需要注意的是, 如果传入的都为 {@code null} 值, 也会返回 {@code true}
   */
  public static boolean isLengthsSame(Collection... collections) {
    int standardLen = ObjContentUtils.isNull(collections[0]) ? INVALID_LEN : collections[0].size();
    for (Collection c : collections) {
      boolean isNullInvalid = (ObjContentUtils.isNull(c) && standardLen != INVALID_LEN);
      boolean isLengthInvalid = (!ObjContentUtils.isNull(c) && c.size() != standardLen);
      if (isNullInvalid || isLengthInvalid) {
        return false;
      }
    }
    return true;
  }
}
