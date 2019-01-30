package com.github.codinghck.base.util.common.base.str;

import com.github.codinghck.base.util.common.lambda.VoidTwoParamExecutor;
import org.springframework.util.StringUtils;

/**
 * @author hck 2019-01-30 15:42
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class StrFillUtils {

  private StrFillUtils() {}

  /**
   * <P>对长度不足的字符串使用字符 {@code 0} 进行左补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @return 补齐后的字符串
   */
  public static String fillLeadingZero(String str, int len) {
    return fillCharacter(str, len, '0', true);
  }

  /**
   * <P>对长度不足的字符串使用字符 {@code 0} 进行右补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @return 补齐后的字符串
   */
  public static String fillTailingZero(String str, int len) {
    return fillCharacter(str, len, '0', false);
  }

  /**
   * <P>对长度不足的字符串使用空格进行左补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @return 补齐后的字符串
   */
  public static String fillLeadingBlank(String str, int len) {
    return fillCharacter(str, len, StrConst.SPACE_CHAR, true);
  }

  /**
   * <P>对长度不足的字符串使用空格进行右补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @return 补齐后的字符串
   */
  public static String fillTailingBlank(String str, int len) {
    return fillCharacter(str, len, StrConst.SPACE_CHAR, false);
  }

  /**
   * <P>对长度不足的字符串进行左补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @param fillChar 用以补齐的字符
   * @return 补齐后的字符串
   */
  public static String fillLeadingCharacter(String str, int len, char fillChar) {
    return fillCharacter(str, len, fillChar, true);
  }

  /**
   * <P>对长度不足的字符串进行右补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @param fillChar 用以补齐的字符
   * @return 补齐后的字符串
   */
  public static String fillTailingCharacter(String str, int len, char fillChar) {
    return fillCharacter(str, len, fillChar, false);
  }

  /**
   * <p>把字符串末尾的空格全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @return 结果字符串
   */
  public static String trimTrailingWhitespace(String str) {
    return trimTrailingChar(str, StrConst.SPACE_CHAR);
  }

  /**
   * <p>把字符串开头的空格全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @return 结果字符串
   */
  public static String trimLeadingWhitespace(String str) {
    return trimLeadingChar(str, StrConst.SPACE_CHAR);
  }

  /**
   * <p>把字符串末尾的 {@code trailing} 字符全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @param trailing 需要删除的字符
   * @return 结果字符串
   */
  public static String trimTrailingChar(String str, char trailing) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && trailing == sb.charAt(sb.length() - 1)) {
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * <p>把字符串开头的 {@code leading} 字符全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @param leading 需要删除的字符
   * @return 结果字符串
   */
  public static String trimLeadingChar(String str, char leading) {
    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuilder sb = new StringBuilder(str);
    while (sb.length() > 0 && leading == sb.charAt(0)) {
      sb.deleteCharAt(0);
    }
    return sb.toString();
  }

  /**
   * <P>对长度不足的字符串进行补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @param fillChar 用以补齐的字符
   * @param isLeading 如果传入值是 {@code true} 则坐补齐, 如果传入值是 {@code false} 则右补齐
   * @return 补齐后的字符串
   */
  public static String fillCharacter(String str, int len, char fillChar, boolean isLeading) {
    StringBuilder sb = new StringBuilder(StrContentUtils.isNull(str) ? StrConst.EMPTY_STRING : str);
    while (sb.length() < len) {
      if (isLeading) {
        sb.insert(0, fillChar);
      } else {
        sb.append(fillChar);
      }
    }
    return sb.toString();
  }

  /**
   * <p>字符串长度不足左侧使用字符 {@code 0} 补长度</p>
   *
   * @param source 源字符串
   * @param needLen 需要的字符串长度
   * @return 补长后的字符串, 如果传入字符串长度超过所需长度, 则返回原字符串
   */
  public static String addZeroLeftIfLenNotEnough(String source, int needLen) {
    return addCharIfLenNotEnough(
        source, '0', needLen, (StringBuilder sb, String str) -> sb.insert(0, str));
  }

  /**
   * <p>字符串长度不足右侧使用字符 {@code 0} 补长度</p>
   *
   * @param source 源字符串
   * @param needLen 需要的字符串长度
   * @return 补长后的字符串, 如果传入字符串长度超过所需长度, 则返回原字符串
   */
  public static String addZeroRightIfLenNotEnough(String source, int needLen) {
    return addCharIfLenNotEnough(source, '0', needLen, StringBuilder::append);
  }

  /**
   * <p>字符串长度不足左侧补长度</p>
   *
   * @param source 源字符串
   * @param addChar 用以补长度的字符
   * @param needLen 需要的字符串长度
   * @return 补长后的字符串, 如果传入字符串长度超过所需长度, 则返回原字符串
   */
  public static String addLeftIfLenNotEnough(String source, char addChar, int needLen) {
    return addCharIfLenNotEnough(
        source, addChar, needLen, (StringBuilder sb, String str) -> sb.insert(0, str));
  }

  /**
   * <p>字符串长度不足右侧补长度</p>
   *
   * @param source 源字符串
   * @param addChar 用以补长度的字符
   * @param needLen 需要的字符串长度
   * @return 补长后的字符串, 如果传入字符串长度超过所需长度, 则返回原字符串
   */
  public static String addRightIfLenNotEnough(String source, char addChar, int needLen) {
    return addCharIfLenNotEnough(source, addChar, needLen, StringBuilder::append);
  }

  /**
   * <p>字符串长度不足补齐方法</p>
   *
   * @param source 源字符串
   * @param addChar 用以补长度的字符
   * @param needLen 需要的字符串长度
   * @param executor 补齐方式
   * @return 补长后的字符串, 如果传入字符串长度超过所需长度, 则返回原字符串
   */
  public static String addCharIfLenNotEnough(
      String source, char addChar, int needLen,
      VoidTwoParamExecutor<StringBuilder, String> executor) {
    int strLen = source.length();
    StringBuilder res = new StringBuilder(source);
    while (strLen < needLen) {
      executor.execute(res, String.valueOf(addChar));
      strLen = res.length();
    }
    return res.toString();
  }
}
