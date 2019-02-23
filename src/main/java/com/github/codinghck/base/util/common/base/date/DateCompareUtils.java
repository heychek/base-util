package com.github.codinghck.base.util.common.base.date;

import com.github.codinghck.base.util.common.base.str.StrConst;
import java.text.ParseException;
import java.util.Date;

/**
 * @author hck 2019-01-30 22:14
 */
@SuppressWarnings({ "unused", "WeakerAccess", "SpellCheckingInspection" })
public class DateCompareUtils {

  private DateCompareUtils() {}

  /**
   * <p>比较两个日期大小</p>
   *
   * @param date1 日期 1
   * @param date2 日期 2
   * @return 如果日期 {@code date1} 早于日期 {@code date2}, 则返回 -1;
   * <p>如果日期 {@code date2} 早于日期 {@code date1}, 则返回 1;
   * <p>如果日期 {@code date1} 等于日期{@code date2}, 则返回 0
   */
  public static int compare(Date date1, Date date2) {
    return Long.compare(date1.getTime(), date2.getTime());
  }

  /**
   * <p>比较两个日期字符串大小</p>
   *
   * @param date1 日期字符串 1
   * @param date2 日期字符串 2
   * @param pattern 日期字符串格式
   * @return 如果日期字符串 {@code date1} 早于日期字符串 {@code date2}, 则返回 -1;
   * <p>如果日期字符串 {@code date2} 早于日期字符串 {@code date1}, 则返回 1;
   * <p>如果日期字符串 {@code date1} 等于日期字符串 {@code date2}, 则返回 0
   * @throws ParseException 当传入日期字符串无法转换时抛出异常
   */
  public static int compare(String date1, String date2, String pattern) throws ParseException {
    return compare(DateFmtUtils.strToDate(date1, pattern), DateFmtUtils.strToDate(date2, pattern));
  }

  /**
   * <p>比较两个日期之间的差距是不是在指定误差毫秒值内</p>
   *
   * @param dateStr1 日期字符串1
   * @param dateStr2 日期字符串2
   * @param millis 误差毫秒值
   * @return 是不是在指定误差内的布尔值结果
   * @throws ParseException 当传入日期字符串无法转换时抛出异常
   */
  public static boolean isDateStrsDiffInRangeMillisDefaultPattern(
      String dateStr1, String dateStr2, long millis) throws ParseException {
    return isDateStrsDiffInRangeMillis(dateStr1, dateStr2, StrConst.NORMAL_DATE_FMT, millis);
  }

  /**
   * <p>比较两个日期之间的差距是不是在指定误差毫秒值内</p>
   *
   * @param dateStr1 日期字符串1
   * @param dateStr2 日期字符串2
   * @param pattern 日期转换格式
   * @param millis 误差毫秒值
   * @return 是不是在指定误差内的布尔值结果
   * @throws ParseException 当传入日期字符串无法转换时抛出异常
   */
  public static boolean isDateStrsDiffInRangeMillis(
      String dateStr1, String dateStr2, String pattern, long millis) throws ParseException {
    return isDatesDiffInRangeMillis(
        DateFmtUtils.strToDate(dateStr1, pattern),
        DateFmtUtils.strToDate(dateStr2, pattern),
        millis
    );
  }

  /**
   * <p>比较两个日期之间的差距是不是在指定误差毫秒值内</p>
   *
   * @param date1 日期1
   * @param date2 日期2
   * @param millis 毫秒值
   * @return 是不是在指定误差内的布尔值结果
   */
  public static boolean isDatesDiffInRangeMillis(Date date1, Date date2, long millis) {
    return Math.abs(date1.getTime() - date2.getTime()) <= Math.abs(millis);
  }

  /**
   * <p>判断传入的日期字符串 {@code time} 根据 {@code pattern} 格式化后的日期是否晚
   * <p>于或等于 {@code start} 格式化后的日期并且早于 {@code end} 格式化后的日期, 即日期 {@code time} 是否
   * <p>处于 {@code start} 和 {@code end} 的左闭右开区间范围内
   *
   * @param time 需要被判断的日期字符串
   * @param start 表示区间开始日期字符串
   * @param end 表示区间结束的日期字符串
   * @param pattern 日期的格式
   * @return 返回表示是否在此区间的布尔值
   * @throws ParseException 当传入的字符串不能根据 {@code pattern} 格式化时抛出异常
   */
  public static boolean isInRange(
      String time, String start, String end, String pattern) throws ParseException {
    return (compare(start, time, pattern) == -1 || compare(start, time, pattern) == 0)
        && compare(end, time, pattern) == 1;
  }
}
