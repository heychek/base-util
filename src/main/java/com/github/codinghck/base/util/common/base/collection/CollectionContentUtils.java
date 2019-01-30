package com.github.codinghck.base.util.common.base.collection;

import com.github.codinghck.base.util.common.base.obj.ObjContentUtils;
import java.util.Collection;

/**
 * @author hck 2019-01-30 20:23
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class CollectionContentUtils {

  private CollectionContentUtils() {}

  /**
   * <p>判断传进来的 {@code Collection} 是否为 null 并且不为空列表</p>
   *
   * @param collection 需要判断的 <code>Collection</code> 对象
   * @return 判断是否为 null 或为空列表的布尔值
   */
  public static boolean isEmpty(Collection collection) {
    return ObjContentUtils.isNull(collection) || collection.isEmpty();
  }

  /**
   * <p>判断传入的所有列表是否有含有其值为 {@code null} 的</p>
   *
   * @param collections 所有传入的 {@code Collection} 对象参数
   * @return 返回值
   */
  public static boolean hasNull(Collection... collections) {
    return ObjContentUtils.hasInValidElement(ObjContentUtils::isNull, collections);
  }

  /**
   * <p>判断传入的所有列表是否有含有其值为 {@code null} 或 {@code size()} 为 0 的</p>
   *
   * @param collections 所有传入的 {@code List} 对象参数
   * @return 返回值
   */
  public static boolean hasEmpty(Collection... collections) {
    return ObjContentUtils.hasInValidElement(CollectionContentUtils::isEmpty, collections);
  }
}
