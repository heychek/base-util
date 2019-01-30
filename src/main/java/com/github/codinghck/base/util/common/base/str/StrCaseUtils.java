package com.github.codinghck.base.util.common.base.str;

import com.github.codinghck.base.util.common.lambda.CharExecutor;

/**
 * @author hck 2019-01-30 16:29
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class StrCaseUtils {

  private StrCaseUtils() {}

  /**
   * <p>将字符串 {@code source} 首字母改为小写</p>
   *
   * @param source 源字符串
   * @return 改变大小写后的结果字符串, 如果传入字符串为 {@code null}, 则返回值也为 {@code null}
   * @throws IndexOutOfBoundsException {@code idx} 下标越界时抛出异常
   */
  public static String toLowerCaseLastOne(String source) {
    if (StrContentUtils.isNull(source)) {
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
    if (StrContentUtils.isNull(source)) {
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
    if (StrContentUtils.isNull(source)) {
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
}
