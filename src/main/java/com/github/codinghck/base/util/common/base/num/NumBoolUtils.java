package com.github.codinghck.base.util.common.base.num;

/**
 * @author hck 2019-01-30 22:28
 */
@SuppressWarnings("unused")
public class NumBoolUtils {

  private NumBoolUtils() {}

  /**
   * <p>判断传入的 {@code int} 类型数字参数是否是用以判断是的数字</p>
   *
   * @param num {@code int} 类型数字参数
   * @return 判断结果
   */
  public static boolean isTrue(int num) {
    return NumBool.TRUE.getBoolKey() == num;
  }

  /**
   * <p>判断传入的 {@code int} 类型数字参数是否是用以判断否的数字</p>
   *
   * @param num {@code int} 类型数字参数
   * @return 判断结果
   */
  public static boolean isFalse(int num) {
    return NumBool.FALSE.getBoolKey() == num;
  }

  /**
   * <p>判断传入的 {@code int} 类型数字参数是否是用以判断是否的数字</p>
   *
   * @param num {@code int} 类型数字参数
   * @return 判断结果
   */
  public static boolean isBool(int num) {
    for (NumBool numBool : NumBool.values()) {
      if (numBool.getBoolKey() == num) {
        return true;
      }
    }
    return false;
  }

  public enum NumBool {
    /**
     * 字符串是否枚举
     */
    TRUE(1),
    FALSE(0);

    private int boolKey;

    NumBool(int boolKey) {
      this.boolKey = boolKey;
    }

    public int getBoolKey() {
      return boolKey;
    }

    public void setBoolKey(int boolKey) {
      this.boolKey = boolKey;
    }
  }
}
