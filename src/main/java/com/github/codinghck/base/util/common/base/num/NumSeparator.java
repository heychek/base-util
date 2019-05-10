package com.github.codinghck.base.util.common.base.num;

import com.github.codinghck.base.util.common.base.character.CharConverter;
import com.github.codinghck.base.util.common.base.str.StrConst;

/**
 * @author hck 2019-05-10 14:21
 */
@SuppressWarnings("WeakerAccess")
public class NumSeparator {

  private NumSeparator() {}

  /**
   * <p>获取字符串表示的数字的整数部分</p>
   *
   * @param num 字符串表示的数字
   * @return 该字符串表示的数字的整数部分的字符串结果
   */
  public static String getIntPart(String num) {
    NumChecker.checkNum(num);
    StringBuilder res = new StringBuilder();
    // 这两个变量主要用来去掉开头的 0
    int t, s = 0;
    for (char c : num.toCharArray()) {
      if (c == StrConst.POINT_SEPARATOR_CHAR) {
        break;
      }
      t = Integer.parseInt(CharConverter.toStr(c), 16);
      if (s + t == 0) {
        continue;
      }
      res.append(t);
      s += t;
    }
    return (res.length() == 0 ? "0" : res.toString());
  }

  /**
   * <p>获取字符串表示的数字的小数部分</p>
   *
   * @param num 字符串表示的数字
   * @return 该字符串表示的数字的整数部分的字符串结果
   * @throws IllegalArgumentException 传入字符串有多个小数点时抛出此异常
   */
  public static String getFraction(String num) {
    int i = num.lastIndexOf(StrConst.POINT_SEPARATOR);
    if (i < 0) {
      return StrConst.EMPTY_STRING;
    }
    if (num.indexOf(StrConst.POINT_SEPARATOR) != i) {
      throw new IllegalArgumentException("数字格式不正确，最多只能有一位小数点!");
    }
    String res = NumSeparator.getIntPart(new StringBuffer(num).reverse().toString());
    if (Integer.valueOf(res) == 0) {
      return StrConst.EMPTY_STRING;
    }
    return new StringBuffer(res).reverse().toString();
  }
}
