package com.github.codinghck.base.util.common.base.character;

import com.github.codinghck.base.util.common.base.str.StrConst;

/**
 * @author hck 2019-05-10 13:48
 */
public class CharConverter {

  private CharConverter() {}

  public static String toStr(char source) {
    return source + StrConst.EMPTY_STRING;
  }
}
