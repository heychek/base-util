package com.github.hckisagoodboy.base.util.common.base;

/**
 * @author hck 2018/11/22 5:02 PM
 */
public class ByteUtils {

  private ByteUtils() {}

  /**
   * <p>从字节数组中截取子数组</p>
   *
   * @param src 源字节数组
   * @param begin 开始位置
   * @param count 截取长度
   * @return 截取结果
   */
  public static byte[] subBytes(byte[] src, int begin, int count) {
    byte[] bs = new byte[count];
    System.arraycopy(src, begin, bs, 0, count);
    return bs;
  }

}
