package com.github.codinghck.base.util.common.base.num;

import com.github.codinghck.base.util.common.base.str.StrConst;

/**
 * @author hck 2019-05-10 14:22
 */
@SuppressWarnings("WeakerAccess")
public class NumChecker {

  private NumChecker() {}

  /**
   * <p>检查字符串, 查看该字符串是否是数字格式的字符串</p>
   *
   * @param num 传入待校验的字符串
   * @throws IllegalArgumentException 当校验失败时抛出此异常
   */
  public static void checkNum(String num) {
    if (num.indexOf(StrConst.POINT_SEPARATOR) != num.lastIndexOf(StrConst.POINT_SEPARATOR)) {
      throw new IllegalArgumentException("数字[" + num + "]格式不正确!");
    }
    if (num.indexOf(StrConst.NUM_MINUS_SIGN)
        != num.lastIndexOf(StrConst.NUM_MINUS_SIGN)
        || num.lastIndexOf(StrConst.NUM_MINUS_SIGN) > 0) {
      throw new IllegalArgumentException("数字[" + num + "]格式不正确！");
    }
    if (num.indexOf(StrConst.NUM_PLUS_SIGN) != num.lastIndexOf(StrConst.NUM_PLUS_SIGN)) {
      throw new IllegalArgumentException("数字[" + num + "]格式不正确！");
    }
    if (num.replaceAll(StrConst.NUM_REGEX, "").length() > 0) {
      throw new IllegalArgumentException("数字[" + num + "]格式不正确！");
    }
  }
}
