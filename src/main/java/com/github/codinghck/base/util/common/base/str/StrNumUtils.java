package com.github.codinghck.base.util.common.base.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hck 2019-01-30 16:32
 */
@SuppressWarnings("unused")
public class StrNumUtils {

  private StrNumUtils() {}

  private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

  /**
   * <p>利用正则表达式判断字符串是否是数字</p>
   *
   * @param str 源字符串
   * @return 结果布尔值
   */
  public static boolean isNumeric(String str) {
    Matcher isNum = NUMBER_PATTERN.matcher(str);
    return isNum.matches();
  }

  /**
   * <p>转换数字字符串的进制</p>
   *
   * @param numStr 数字字符串
   * @param notation 需要转换成的进制
   * @return 转换结果
   */
  public static String changeNotation(String numStr, int notation) {
    return String.valueOf(Integer.parseInt(numStr, notation));
  }
}
