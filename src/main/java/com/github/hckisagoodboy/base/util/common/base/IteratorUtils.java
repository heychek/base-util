package com.github.hckisagoodboy.base.util.common.base;

import com.github.hckisagoodboy.base.util.common.exception.UnExpectedException;
import java.util.Iterator;

/**
 * @author hck
 * 2018/11/2 4:57 PM
 */
public class IteratorUtils {

  private IteratorUtils() {}
  /**
   * 移动迭代器的指针指定次数 {@code offset}
   * @param it 传入迭代器
   * @param offset 偏移量
   * @throws UnExpectedException 如果偏移量小于 0 或大于迭代器长度, 则抛出异常
   */
  public static void moveIteratorPointer(Iterator<String> it, int offset)
      throws UnExpectedException {
    if (offset < 0) {
      throw new UnExpectedException("properties index 越界");
    }

    for (int i = 0; i < offset; i ++) {
      if (it.hasNext()) {
        it.next();
      } else {
        throw new UnExpectedException("properties index 越界");
      }
    }
  }
}
