package com.github.codinghck.base.util.common.base.str;

import org.springframework.util.StringUtils;

/**
 * @author hck 2018/11/2 1:28 PM
 */
@SuppressWarnings({ "unused"})
public class StrSubUtils {

  public StrSubUtils() {}

  /**
   * <p>截取字符串</p>
   *
   * @param str 待截取的字符串
   * @param start 截取起始位置 (1 表示第一位 -1 表示倒数第1位)
   * @param end 截取结束位置 (如上index)
   * @return 截取结果
   */
  public static String sub(String str, int start, int end) {
    if (!StringUtils.hasText(str)) {
      return "";
    }

    start = start < 0 ? str.length() + start : start - 1;
    end = end < 0 ? str.length() + end + 1 : end;
    return str.substring(start, end);
  }
}
