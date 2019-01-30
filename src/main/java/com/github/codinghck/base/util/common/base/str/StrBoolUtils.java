package com.github.codinghck.base.util.common.base.str;

/**
 * @author hck 2019-01-30 16:33
 */
@SuppressWarnings("unused")
public class StrBoolUtils {

  private StrBoolUtils() {}

  /**
   * <p>布尔值转换为用以判断是的字符串</p>
   *
   * @param isTrue 源布尔值
   * @return 转换结果
   */
  public static String toBoolStr(boolean isTrue) {
    return isTrue ? StringBool.TRUE.getBoolKey() : StringBool.FALSE.getBoolKey();
  }

  /**
   * <p>判断传入的字符串是否是用以判断是的字符串</p>
   *
   * @param str 字符串参数
   * @return 判断结果
   */
  public static boolean isTrue(String str) {
    return StringBool.TRUE.getBoolKey().equals(str);
  }

  /**
   * <p>判断传入的字符串是否是用以判断否的字符串</p>
   *
   * @param str 字符串参数
   * @return 判断结果
   */
  public static boolean isFalse(String str) {
    return StringBool.FALSE.getBoolKey().equals(str);
  }

  /**
   * <p>判断传入的字符串是否是用以判断是否的字符串</p>
   *
   * @param str 字符串参数
   * @return 判断结果
   */
  public static boolean isBool(String str) {
    if (StrContentUtils.isNull(str)) {
      return false;
    }
    for (StringBool stringBool : StringBool.values()) {
      if (stringBool.getBoolKey().equals(str)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 字符串表示是否的枚举类
   */
  public enum StringBool {
    /**
     * 字符串表示是否的枚举对象
     */
    TRUE("1"),
    FALSE("0");

    private String boolKey;

    StringBool(String boolKey) {
      this.boolKey = boolKey;
    }

    public String getBoolKey() {
      return boolKey;
    }

    public void setBoolKey(String boolKey) {
      this.boolKey = boolKey;
    }
  }
}
