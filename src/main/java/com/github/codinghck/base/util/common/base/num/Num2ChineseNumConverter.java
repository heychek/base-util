package com.github.codinghck.base.util.common.base.num;

import com.github.codinghck.base.util.common.base.character.CharConverter;
import com.github.codinghck.base.util.common.base.str.StrConst;
import java.util.Arrays;
import org.springframework.util.StringUtils;

/**
 * @author hck 2019-05-10 10:59
 */
@SuppressWarnings({"unused"})
public class Num2ChineseNumConverter {

  private Num2ChineseNumConverter() {}

  /**
   * <p>阿拉伯数字转换为中文小写数字</p>
   *
   * @param num 将要转化的表示数字的对象
   * @throws IllegalArgumentException 如果传入的对象不为 {@link Integer}, {@link Long}, {@link
   * java.math.BigDecimal}, {@link Float}, {@link Double}, {@link Double},{@link String}, {@link
   * Byte}, {@link Short}, {@link java.math.BigInteger}, {@link Number} 之一或传入对象 如 {@link String}
   * 无法转换为相应数字, 将抛出 {@link IllegalArgumentException} 异常
   * @return 转换结果
   */
  public static String toChineseLower(Object num) {
    return format(num, StrConst.CHINESE_NUM_LOWER, StrConst.CHINESE_NUM_UNIT_LOWER);
  }

  /**
   * <p>阿拉伯数字转换为中文大写数字, 多用于财务的金额表示</p>
   *
   * @param num 将要转化的表示数字的对象
   * @throws IllegalArgumentException 如果传入的对象不为 {@link Integer}, {@link Long}, {@link
   * java.math.BigDecimal}, {@link Float}, {@link Double}, {@link Double},{@link String}, {@link
   * Byte}, {@link Short}, {@link java.math.BigInteger}, {@link Number} 之一或传入对象 如 {@link String}
   * 无法转换为相应数字, 将抛出 {@link IllegalArgumentException} 异常
   * @return 转换结果
   */
  public static String toChineseUpper(Object num) {
    return format(num, StrConst.CHINESE_NUM_UPPER, StrConst.CHINESE_NUM_UNIT_UPPER);
  }

  /**
   * <p>阿拉伯数字转换为中文大写数字, 多用于财务的金额表示</p>
   *
   * @param num 将要转化的表示数字的对象
   * @param numArray 数字大小写数组
   * @param unit 单位权值
   * @return 中文大写数字的转换结果
   */
  private static String format(Object num, String[] numArray, String[] unit) {
    if (!Arrays.asList(StrConst.NUM_TYPES).contains(num.getClass().getSimpleName().toUpperCase())) {
      throw new IllegalArgumentException("不支持的格式类型");
    }
    String intPart = NumSeparator.getIntPart(String.valueOf(num));
    String decimalPart = NumSeparator.getFraction(String.valueOf(num));
    String result = formatIntPart(intPart, numArray, unit);
    if (StringUtils.hasText(decimalPart) && Integer.valueOf(decimalPart) != 0) {
      result += StrConst.CHINESE_NUM_DOT + formatFractionalPart(decimalPart, numArray);
    }
    return result;
  }

  /**
   * <p>格式化整数部分</p>
   *
   * @param num 整数部分数字字符串
   * @param numArray 表示汉字数字大或小写的数组
   * @param unit 表示汉字数字单位的数组
   * @return 格式化结果
   */
  private static String formatIntPart(String num, String[] numArray, String[] unit) {
    Integer[] intNums = split2IntArray(num);
    boolean zero = false;
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < intNums.length; i++) {
      String oneGroupRes = formatOneGroupInt(intNums[i], numArray, unit);
      if (StrConst.EMPTY_STRING.equals(oneGroupRes)) {
        if ((i + 1) == intNums.length) {
          res.append(numArray[0]);
        } else {
          zero = true;
        }
      } else {
        if (zero || (i > 0 && intNums[i] < 1000)) {
          res.append(numArray[0]);
        }
        res.append(oneGroupRes);
        res.append(StrConst.CHINESE_NUM_UNIT_COMMON[intNums.length - 1 - i]);
        zero = false;
      }
    }
    return res.toString();
  }

  /**
   * 格式化小数部分
   *
   * @param decimal 小数部分
   * @param numArray 数字大小写数组
   * @return 格式化结果
   */
  private static String formatFractionalPart(String decimal, String[] numArray) {
    char[] val = String.valueOf(decimal).toCharArray();
    int len = val.length;
    StringBuilder sb = new StringBuilder();
    for (char c : val) {
      int n = Integer.valueOf(c + "");
      sb.append(numArray[n]);
    }
    return sb.toString();
  }

  /**
   * <p>分割数字, 每 4 位一组, 不足四位的前面补 0</p>
   *
   * @param num 传入的待分割的数字字符串
   * @return 结果数组
   */
  private static Integer[] split2IntArray(String num) {
    int groupLen = 4;
    String prev = num.substring(0, num.length() % groupLen);
    String stuff = num.substring(num.length() % groupLen);
    if (!StrConst.EMPTY_STRING.equals(prev)) {
      num = String.format("%04d", Integer.valueOf(prev)) + stuff;
    }
    Integer[] ints = new Integer[num.length() / groupLen];
    int idx = 0;
    for (int i = 0; i < num.length(); i += groupLen) {
      String n = num.substring(i, i + groupLen);
      ints[idx++] = Integer.valueOf(n);
    }
    return ints;
  }

  /**
   * <p>格式化 4 位整数(一组)</p>
   *
   * @param num 数字字符串
   * @param numArray 表示汉字数字大或小写的数组
   * @param unit 表示汉字数字单位的数组
   * @return 格式化结果
   */
  private static String formatOneGroupInt(int num, String[] numArray, String[] unit) {
    char[] val = String.valueOf(num).toCharArray();
    int len = val.length;
    StringBuilder res = new StringBuilder();
    boolean isZero = false;
    for (int i = 0; i < len; i++) {
      int n = Integer.valueOf(CharConverter.toStr(val[i]));
      if (n == 0) {
        isZero = true;
      } else {
        if (isZero) {
          res.append(numArray[Integer.valueOf(CharConverter.toStr(val[i - 1]))]);
        }
        res.append(numArray[n]);
        res.append(unit[(len - 1) - i]);
        isZero = false;
      }
    }
    return res.toString();
  }
}
