package com.github.codinghck.base.util.common.base.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author hck 2019-02-14 11:31
 */
@SuppressWarnings("unused")
public class StrCollectionConverter {

  private StrCollectionConverter() {}

  private static final char ARR_START_SYMBOL = '[';
  private static final char ARR_END_SYMBOL = ']';

  /**
   * <p>根据字符串的分隔符将字符串转换为 set</p>
   *
   * @param content 字符串
   * @param strSeparator 字符串分隔符
   * @return set 对象
   */
  public static Set<String> str2Set(String content, String strSeparator) {
    if (content == null || content.length() == 0) {
      return new HashSet<>();
    }
    String[] arr = content.split(strSeparator);

    return new HashSet<>(Arrays.asList(arr));
  }

  /**
   * <p>根据字符串的默认分隔符将字符串转换为 set</p>
   *
   * @param content 字符串
   * @return set 对象
   */
  public static Set<String> str2Set(String content) {
    return str2Set(content, StrConst.DOT_SEPARATOR);
  }

  /**
   * <p>根据字符串的分隔符将字符串转换为 List</p>
   *
   * @param content 字符串
   * @param strSeparator 字符串分隔符
   * @return List 对象
   */
  public static List<String> str2List(String content, String strSeparator) {
    if (content == null || content.length() == 0) {
      return new ArrayList<>();
    }
    String copy = content;
    if (copy.charAt(0) == ARR_START_SYMBOL) {
      copy = copy.substring(1);
    }
    if (copy.length() > 0 && copy.charAt(copy.length() - 1) == ARR_END_SYMBOL) {
      copy = copy.substring(0, copy.length() -1);
    }
    String[] arr = copy.split(strSeparator);
    return Arrays.asList(arr);
  }

  /**
   * <p>根据字符串的默认分隔符将字符串转换为 List</p>
   *
   * @param content 字符串
   * @return List 对象
   */
  public static List<String> str2List(String content) {
    return str2List(content, StrConst.DOT_SEPARATOR);
  }
}
