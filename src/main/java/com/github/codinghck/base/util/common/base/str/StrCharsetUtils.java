package com.github.codinghck.base.util.common.base.str;

import org.springframework.util.StringUtils;

/**
 * @author hck 2019-01-30 16:43
 */
@SuppressWarnings("unused")
public class StrCharsetUtils {

  private StrCharsetUtils() {}

  /**
   * <p>将字符串 {@code str} 格式转为 {@code utf-8} 格式</p>
   *
   * @param str 字符串
   * @return 转换结果字符串
   */
  public static String iso2Utf8(String str) {
    if (StringUtils.isEmpty(str)) {
      return str;
    }
    return new String(
        str.getBytes(StrCommonCharset.ISO_8859_1.charset()), StrCommonCharset.UTF_8.charset()
    );
  }

  /**
   * <p>将字符串 {@code str} 格式转为 {@code utf-8} 格式</p>
   *
   * @param str 字符串
   * @return 转换结果字符串
   */
  public static String gbk2Utf8(String str) {
    if (StringUtils.isEmpty(str)) {
      return str;
    }
    return new String(
        str.getBytes(StrCommonCharset.GBK.charset()), StrCommonCharset.UTF_8.charset()
    );
  }
}
