package com.github.codinghck.base.util.common.base.date;

import com.github.codinghck.base.util.common.base.str.StrConst;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hck
 * 2018/11/3 10:28 PM
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class DateFmtUtils {

  private DateFmtUtils() {}

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
   * <p>将传入的秒值转换为 {@code Date} 类型对象</p>
   *
   * @param second 需被转换的秒值
   * @return 转换后 {@code Date} 类型对象
   */
  public static Date second2Date(long second) {
    return millis2Date(second * 1000);
  }

  /**
   * <p>将传入的毫秒值转换为 {@code Date} 类型对象</p>
   *
   * @param millis 需被转换的毫秒值
   * @return 转换后 {@code Date} 类型对象
   */
  public static Date millis2Date(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();
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
    return strToDate(date, StrConst.NORMAL_DATE_FMT);
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
    return dateToStr(date, StrConst.NORMAL_DATE_FMT);
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
}
