package com.github.codinghck.base.util.common.base.str;

/**
 * @author hck 2019-01-30 16:28
 */
@SuppressWarnings({ "unused", "WeakerAccess", "SpellCheckingInspection" })
public class StrConst {

  private StrConst() {}

  public static final char SPACE_CHAR = ' ';
  public static final char POINT_SEPARATOR_CHAR = '.';
  public static final String EMPTY_STRING = "";
  public static final String UNDER_LINE_SEPARATOR = "_";
  public static final String POINT_SEPARATOR = ".";
  public static final String DOT_SEPARATOR = ",";
  public static final String NORMAL_DATE_FMT = "yyyy-MM-dd HH:mm:ss";
  public static final String EMPTY_OBJ_STR = "{}";
  public static final String DEFAULT_SUCCESS_MSG = "success";
  public static final String DEFAULT_FAIL_MSG = "fail";
  public static final String NUM_REGEX = "[\\d|.|\\-|+]";
  public static final String NUM_PLUS_SIGN = "+";
  public static final String NUM_MINUS_SIGN = "-";
  public static final String CHINESE_NUM_DOT = "点";
  public static final String[] CHINESE_NUM_LOWER = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
  public static final String[] CHINESE_NUM_UPPER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
  public static final String[] CHINESE_NUM_UNIT_LOWER = { "", "十", "百", "千" };
  public static final String[] CHINESE_NUM_UNIT_UPPER = { "", "拾", "佰", "仟"};
  public static final String[] CHINESE_NUM_UNIT_COMMON = {"","万", "亿","兆","京","垓","秭","穰","沟","涧","正","载"};
  public static final String[] NUM_TYPES = { "INTEGER", "INT", "LONG", "BIGDECIMAL", "FLOAT", "DOUBLE", "DOUBLE", "STRING", "BYTE", "BIGINTEGER", "SHORT", "NUMBER"};
}
