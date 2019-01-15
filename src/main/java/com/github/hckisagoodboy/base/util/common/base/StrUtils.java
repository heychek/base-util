package com.github.hckisagoodboy.base.util.common.base;

import com.github.hckisagoodboy.base.util.common.lambda.CharExecutor;
import com.github.hckisagoodboy.base.util.common.lambda.VoidTwoParamExecutor;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

/**
 * @author hck 2018/11/2 1:28 PM
 */
public class StrUtils {

  public StrUtils() {}

  public static final String EMPTY_STRING = "";
  public static final char WHITE_SPACE = ' ';
  public static final char ZERO = '0';
  private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");

  /**
   * <p>利用正则表达式判断字符串是否是数字</p>
   *
   * @param str 源字符串
   * @return 结果布尔值
   */
  public static boolean isNumeric(String str) {
    Matcher isNum = NUMBER_PATTERN.matcher(str);
    return isNum.matches();
  }

  /**
   * <P>对长度不足的字符串使用字符 {@code 0} 进行左补齐
   * <p>源字符串可以为 {@code null}
   *
   * @param str 源字符串
   * @param len 字符串需要的长度
   * @return 补齐后的字符串
   */
  public static String fillLeadingZero(String str, int len) {
    return fillCharacter(str, len, ZERO, true);
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
    return fillCharacter(str, len, ZERO, false);
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
    return fillCharacter(str, len, WHITE_SPACE, true);
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
    return fillCharacter(str, len, WHITE_SPACE, false);
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
    StringBuilder sb = new StringBuilder(isNull(str) ? EMPTY_STRING : str);
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
   * <p>把字符串末尾的空格全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @return 结果字符串
   */
  public static String trimTrailingWhitespace(String str) {
    return trimTrailingChar(str, WHITE_SPACE);
  }

  /**
   * <p>把字符串开头的空格全部删除并返回新字符串</p>
   *
   * @param str 源字符串
   * @return 结果字符串
   */
  public static String trimLeadingWhitespace(String str) {
    return trimLeadingChar(str, WHITE_SPACE);
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
   * <p>截取字符串</p>
   *
   * @param str 待截取的字符串
   * @param start 截取起始位置 (1 表示第一位 -1 表示倒数第1位)
   * @param end 截取结束位置 (如上index)
   * @return 截取结果
   */
  public static String sub(String str, int start, int end) {
    if (!StringUtils.hasText(str)) {
      return "";
    }

    start = start < 0 ? str.length() + start : start - 1;
    end = end < 0 ? str.length() + end + 1 : end;
    return str.substring(start, end);
  }

  /**
   * <p>将字符串 {@code source} 首字母改为小写</p>
   *
   * @param source 源字符串
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toLowerCaseLastOne(String source) {
    if (isNull(source)) {
      return null;
    }
    return toLowerCase(source, source.length() - 1);
  }

  /**
   * <p>将字符串 {@code source} 首字母改为大写</p>
   *
   * @param source 源字符串
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toUpperCaseLastOne(String source) {
    if (isNull(source)) {
      return null;
    }
    return toUpperCase(source, source.length() - 1);
  }

  /**
   * <p>将字符串 {@code source} 首字母改为小写</p>
   *
   * @param source 源字符串
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toLowerCaseFirstOne(String source) {
    return toLowerCase(source, 1);
  }

  /**
   * <p>将字符串 {@code source} 首字母改为大写</p>
   *
   * @param source 源字符串
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toUpperCaseFirstOne(String source) {
    return toUpperCase(source, 1);
  }

  /**
   * <p>将字符串 {@code source} 某个位置 {@code idx} 的字符改为小写</p>
   *
   * @param source 源字符串
   * @param idx 字符位置
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toLowerCase(String source, int idx) {
    return changeCase(Character::toLowerCase, source, idx);
  }

  /**
   * <p>将字符串 {@code source} 某个位置 {@code idx} 的字符改为大写</p>
   *
   * @param source 源字符串
   * @param idx 字符位置
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toUpperCase(String source, int idx) {
    return changeCase(Character::toUpperCase, source, idx);
  }

  /**
   * <p>将字符串 {@code source} 某个位置 {@code idx} 的字符改变大小写</p>
   *
   * @param executor 决定具体改变大写还是小写
   * @param source 源字符串
   * @param idx 字符位置
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String changeCase(CharExecutor<Character> executor, String source, int idx) {
    if (isNull(source)) {
      return null;
    }
    if (idx > source.length() - 1) {
      throw new IndexOutOfBoundsException("字符串下标越界, 请检查字符串长度和位置参数 idx");
    }
    StringBuilder res = new StringBuilder();
    res.append(source, 0, idx + 1)
        .append(executor.execute(source.charAt(idx)));
    if (idx != source.length() - 1) {
      res.append(source, idx + 1, source.length());
    }

    return res.toString();
  }

  /**
   * <p>将字符串 {@code str} 格式转为 {@code utf-8} 格式</p>
   *
   * @param str 字符串
   * @return 转换结果字符串
   */
  public static String toutf8(String str) {
    try {
      if (StringUtils.isEmpty(str)) {
        return str;
      }
      return new String(str.getBytes("iso-8859-1"), "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * <p>仅判断字符串是否为 null</p>
   *
   * @param str 待判断字符串
   * @return 该字符串是否为 null 的结果
   */
  public static boolean isNull(String str) {
    return null == str;
  }

  /**
   * <p>仅判断字符串是否不为 null</p>
   *
   * @param str 待判断字符串
   * @return 该字符串是否不为 null 的结果
   */
  public static boolean isNotNull(String str) {
    return null != str;
  }

  /**
   * <p>判断传入的字符串 {@code str} 长度是否大于传入的值 {@code len}</p>
   *
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code false}, 正常情况下返回长度比较结果
   */
  public static boolean isOutLen(String str, int len) {
    if (isNull(str)) {
      return false;
    }
    return str.length() > len;
  }

  /**
   * <p>判断传入的字符串 {@code str} 长度是否小于等于传入的值 {@code len}</p>
   *
   * @param str 传入的字符串
   * @param len 该字符串长度的比较值
   * @return 字符串为 null 时返回 {@code true}, 正常情况下返回长度比较结果
   */
  public static boolean isBelowLen(String str, int len) {
    if (isNull(str)) {
      return true;
    }
    return str.length() < len;
  }

  /**
   * <p>判断传入字符串 {@code str} 的长度是否大于等于 {@code min} 并小于 {@code max},
   * <p>即判断字符串 {@code str} 的长度是否处于 [{@code min}, {@code max}) 区间内
   *
   * @param str 传入的字符串
   * @param min 字符串所允许的长度最小值, 可以相等
   * @param max 字符串所允许的长度最大值, 不可以相等
   * @return 字符串 {@code str} 的长度是否处于 [{@code min}, {@code max}) 区间内的布尔值结果
   */
  public static boolean isLenInRange(String str, int min, int max) {
    return !isOutLen(str, max - 1) && !isBelowLen(str, min);
  }

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 的字符串的布尔值
   */
  public static boolean hasNull(String... strs) {
    return ObjUtils.hasInValidElement(StrUtils::isNotNull, strs);
  }

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 或值为长度为 0 的字符串的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 或值为长度为 0 的字符串的布尔值
   */
  public static boolean hasEmpty(String... strs) {
    return ObjUtils.hasInValidElement(StringUtils::hasLength, strs);
  }

  /**
   * <p>判断传进来的所有字符串参数是否含有为 null 或值为长度为 0 或只包含空格的字符串的对象</p>
   *
   * @param strs 需要判断的所有 <code>String</code> 对象
   * @return 判断是否含有为 null 或值为长度为 0 或只包含空格的字符串的布尔值
   */
  public static boolean hasBlank(String... strs) {
    return ObjUtils.hasInValidElement(StringUtils::hasText, strs);
  }

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
    if (isNull(str)) {
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
   * <p>转换数字字符串的进制</p>
   *
   * @param numStr 数字字符串
   * @param notation 需要转换成的进制
   * @return 转换结果
   */
  public static String changeNotation(String numStr, int notation) {
    return String.valueOf(Integer.parseInt(numStr, notation));
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

  /**
   * 字符串表示是否的枚举类
   */
  public enum StringBool {
    /**
     * 字符串是否的枚举对象
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
