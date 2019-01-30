package com.github.codinghck.base.util.common.base.str;

/**
 * @author hck 2019-01-30 16:41
 */
@SuppressWarnings({"unused", "WeakerAccess" })
public class StrLenUtils {

  private StrLenUtils() {}

  /**
   * <p>判断传入的字符串 {@code str} 长度是否大于传入的值 {@code len}</p>
   *
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code false}, 正常情况下返回长度比较结果
   */
  public static boolean isOutLen(String str, int len) {
    if (StrContentUtils.isNull(str)) {
      return false;
    }
    return str.length() > len;
  }

  /**
   * <p>判断传入的字符串 {@code str} 长度是否小于等于传入的值 {@code len}</p>
   *
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code true}, 正常情况下返回长度比较结果
   */
  public static boolean isBelowLen(String str, int len) {
    if (StrContentUtils.isNull(str)) {
      return true;
    }
    return str.length() < len;
  }

  /**
   * <p>判断传入字符串 {@code str} 的长度是否大于等于 {@code min} 并小于 {@code max},
   * <p>即判断字符串 {@code str} 的长度是否处于 [{@code min}, {@code max}) 区间内
   *
   * @param str 传入的字符串
   * @param min 字符串所允许的长度最小值, 可以相等
   * @param max 字符串所允许的长度最大值, 不可以相等
   * @return 字符串 {@code str} 的长度是否处于 [{@code min}, {@code max}) 区间内的布尔值结果
   */
  public static boolean isLenInRange(String str, int min, int max) {
    return !isOutLen(str, max - 1) && !isBelowLen(str, min);
  }
}
