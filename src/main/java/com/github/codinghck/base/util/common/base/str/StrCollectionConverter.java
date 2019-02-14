package com.github.codinghck.base.util.common.base.str;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hck 2019-02-14 11:31
 */
public class StrCollectionConverter {

  private StrCollectionConverter() {}

  /**
   * <p>字符串转换为 set</p>
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
}
