package com.github.hckisagoodboy.base.util.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hck
 * @date 2018/11/3 10:28 PM
 */
public class DateUtils {

  private DateUtils() {}

  private static final String DEFAULT_PATTERN = "yyyy-MM-dd hh:mm:ss";

  /**
   * <p>将日期字符串根据传入的格式转换成毫秒值</p>
   *
   * @param dateStr 需要被转换的日期字符串
   * @param pattern 该日期字符串的格式
   * @return 转换后的毫秒值
   * @throws ParseException 如果该日期字符串不符合该日期格式, 则抛出异常
   */
  public static long dateStrToMills(String dateStr, String pattern) throws ParseException {
    Date date = strToDate(dateStr, pattern);
    return date.getTime();
  }

  /**
   * <p>将日期字符串根据传入的格式转换成秒值</p>
   *
   * @param dateStr 需要被转换的日期字符串
   * @param pattern 该日期字符串的格式
   * @return 转换后的秒值
   * @throws ParseException 如果该日期字符串不符合该日期格式, 则抛出异常
   */
  public static long dateStrToSecond(String dateStr, String pattern) throws ParseException {
    return dateStrToMills(dateStr, pattern) / 1000;
  }

  /**
   * <p>根据传入格式将字符串参数转换为 <code>Date</code> 对象</p>
   *
   * @param date 符合 {@code pattern} 格式的日期字符串
   * @param pattern 需要转化的该日期字符串的日期格式
   * @return 转换后的 <code>Date</code> 对象
   * @throws ParseException 如果该日期字符串不符合该日期格式, 则抛出异常
   */
  public static Date strToDate(String date, String pattern) throws ParseException {
    SimpleDateFormat fmt = new SimpleDateFormat(pattern);
    return fmt.parse(date);
  }

  /**
   * <p>根据默认格式将字符串参数转换为 <code>Date</code> 对象</p>
   *
   * @param date 符合默认格式的日期字符串
   * @return 转换后的 <code>Date</code> 对象
   * @throws ParseException 如果该日期字符串不符合默认日期格式, 则抛出异常
   */
  public static Date strToDateDefaultPattern(String date) throws ParseException {
    return strToDate(date, DEFAULT_PATTERN);
  }

  /**
   * <p>根据传入格式将日期参数转换为日期字符串</p>
   *
   * @param date 需要转换的的日期
   * @param pattern 需要转化的该日期字符串的日期格式
   * @return 转换后的日期字符串
   */
  public static String dateToStr(Date date, String pattern) {
    DateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
  }

  /**
   * <p>根据传入格式将日期参数转换为日期字符串</p>
   *
   * @param date 需要转换的的日期
   * @return 转换后的日期字符串
   */
  public static String dateToStrDefaultPattern(Date date) {
    return dateToStr(date, DEFAULT_PATTERN);
  }

  /**
   * <p>获取当前系统时间的秒值</p>
   *
   * @return 当前系统时间的秒值
   */
  public static long currentTimeSeconds() {
    return System.currentTimeMillis() / 1000;
  }

  /**
   * <p>将日期字符串根据 {@code pattern} 格式转成另一个符合格式的日期字符串</p>
   *
   * @param date 需要被转换的日期字符串
   * @param pattern 需要被转成的格式
   * @return 符合格式的日期字符串
   * @throws ParseException 当传入日期字符串无法转换时抛出异常
   */
  public static String changeDateStr(String date, String pattern) throws ParseException {
    Date d = strToDate(date, pattern);
    return dateToStr(d, pattern);
  }

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
   * @return 如果日期字符串 {@code date1} 早于日期字符串 {@code date2}, 则返回 -1;
   * <p>如果日期字符串 {@code date2} 早于日期字符串 {@code date1}, 则返回 1;
   * <p>如果日期字符串 {@code date1} 等于日期字符串 {@code date2}, 则返回 0
   * @throws ParseException 当传入日期字符串无法转换时抛出异常
   */
  public static int compare(String date1, String date2, String pattern) throws ParseException {
    return compare(strToDate(date1, pattern), strToDate(date2, pattern));
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

  /**
   * <p>根据默认的日期格式获取当前的日期字符串</p>
   *
   * @return 当前的日期字符串
   */
  public static String currTimeStrDefaultPattern() {
    return currTimeStr(DEFAULT_PATTERN);
  }

  /**
   * <p>根据传入的日期格式获取当前的日期字符串</p>
   *
   * @return 当前的日期字符串
   */
  public static String currTimeStr(String pattern) {
    return new SimpleDateFormat(pattern).format(new Date());
  }
}
