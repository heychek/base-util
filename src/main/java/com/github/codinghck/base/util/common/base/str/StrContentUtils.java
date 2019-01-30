package com.github.codinghck.base.util.common.base.str;

import com.github.codinghck.base.util.common.base.ObjUtils;
import org.springframework.util.StringUtils;

/**
 * @author hck 2019-01-30 15:45
 */
@SuppressWarnings({ "WeakerAccess", "unused", "SpellCheckingInspection" })
public class StrContentUtils {

  private StrContentUtils() {}

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
   * <p>判断传进来的所有字符串参数是否含有为 null 的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 的字符串的布尔值
   */
  public static boolean hasNull(String... strs) {
    return ObjUtils.hasInValidElement(StrContentUtils::isNotNull, strs);
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
