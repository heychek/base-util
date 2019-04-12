package com.github.codinghck.base.util.common.base.str;

import org.springframework.util.StringUtils;

/**
 * @author hck 2019-03-12 17:19
 */
@SuppressWarnings("unused")
public class StrFmtUtils {

  private static final String BYTE_DATA_4;

  static {
    BYTE_DATA_4 = "[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]";
  }

  /**
   * <p>把字符串中的特殊符号如 emoj 表情删除</p>
   *
   * @param str 源字符串
   * @return 替换结果
   */
  public static String replaceByteDate4(String str) {
    return StringUtils.isEmpty(str) ? str : str.replaceAll(BYTE_DATA_4, StrConst.EMPTY_STRING);
  }

  /**
   * <p>把字符串中的特殊符号如 emoj 表情替换</p>
   *
   * @param str 源字符串
   * @param target 需要替换的字符串
   * @return 替换结果
   */
  public static String replaceByteDate4(String str, String target) {
    return StringUtils.isEmpty(str) ? str : str.replaceAll(BYTE_DATA_4, target);
  }
}
