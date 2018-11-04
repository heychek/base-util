package com.github.hckisagoodboy.base.util.common.util;

import java.io.UnsupportedEncodingException;
import org.springframework.util.StringUtils;

/**
 * @author hck
 * 2018/11/2 1:28 PM
 */
public class StrUtils {

  public StrUtils() {}
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

  /**
   * <p>将字符串 {@code str} 格式转为 {@code utf-8} 格式</p>
   *
   * @param str 字符串
   * @return 转换结果字符串
   */
  public static String toutf8(String str) {
    try {
      if (StringUtils.isEmpty(str)) {
        return str;
      }
      return new String(str.getBytes("iso-8859-1"), "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * <p>仅判断字符串是否为 null</p>
   *
   * @param str 待判断字符串
   * @return 该字符串是否为 null 的结果
   */
  public static boolean isNull(String str) {
    return null == str;
  }

  /**
   * <p>仅判断字符串是否不为 null</p>
   *
   * @param str 待判断字符串
   * @return 该字符串是否不为 null 的结果
   */
  public static boolean isNotNull(String str) {
    return null != str;
  }

  /**
   * <p>判断传入的字符串 {@code str} 长度是否大于传入的值 {@code len}</p>
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code false}, 正常情况下返回长度比较结果
   */
  public static boolean isOutLen(String str, int len) {
    if (isNull(str)) {
      return false;
    }
    return str.length() > len;
  }

  /**
   * <p>判断传入的字符串 {@code str} 长度是否小于等于传入的值 {@code len}</p>
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code true}, 正常情况下返回长度比较结果
   */
  public static boolean isBelowLen(String str, int len) {
    if (isNull(str)) {
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

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 的字符串的布尔值
   */
  public static boolean hasNull(String... strs) {
    return ObjUtils.hasInValidElement(StrUtils::isNotNull, strs);
  }

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 或值为长度为 0 的字符串的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 或值为长度为 0 的字符串的布尔值
   */
  public static boolean hasEmpty(String... strs) {
    return ObjUtils.hasInValidElement(StringUtils::hasLength, strs);
  }

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 或值为长度为 0 或只包含空格的字符串的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 或值为长度为 0 或只包含空格的字符串的布尔值
   */
  public static boolean hasBlank(String... strs) {
    return ObjUtils.hasInValidElement(StringUtils::hasText, strs);
  }
}
