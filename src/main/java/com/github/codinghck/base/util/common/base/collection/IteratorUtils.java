package com.github.codinghck.base.util.common.base.collection;

import java.util.Iterator;

/**
 * @author hck
 * 2018/11/2 4:57 PM
 */
public class IteratorUtils {

  private IteratorUtils() {}

  /**
   * <p>移动迭代器的指针指定次数 {@code offset}</p>
   *
   * @param it 传入迭代器
   * @param offset 偏移量
   * @throws IndexOutOfBoundsException 如果偏移量小于 0 或大于迭代器长度, 则抛出异常
   */
  public static void moveIteratorPointer(Iterator<String> it, int offset) {
    if (offset < 0) {
      throw new IndexOutOfBoundsException("properties index 越界");
    }

    for (int i = 0; i < offset; i ++) {
      if (it.hasNext()) {
        it.next();
      } else {
        throw new IndexOutOfBoundsException("properties index 越界");
      }
    }
  }
}
