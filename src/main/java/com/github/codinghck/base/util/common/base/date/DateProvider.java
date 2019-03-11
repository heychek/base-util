package com.github.codinghck.base.util.common.base.date;

import com.github.codinghck.base.util.common.base.str.StrConst;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hck 2019-01-30 22:16
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class DateProvider {

  private DateProvider() {}

  /**
   * <p>根据默认的日期格式获取当前的日期字符串</p>
   *
   * @return 当前的日期字符串
   */
  public static String currTimeStrDefaultPattern() {
    return currTimeStr(StrConst.NORMAL_DATE_FMT);
  }

  /**
   * <p>根据传入的日期格式获取当前的日期字符串</p>
   *
   * @param pattern 需要转换的日期字符串格式
   * @return 当前的日期字符串
   */
  public static String currTimeStr(String pattern) {
    return new SimpleDateFormat(pattern).format(new Date());
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
   * <p>获取上一个工作日</p>
   *
   * @return 上一个工作日的日期
   */
  public static Date preWeekDay() {
    Date today = new Date();
    WeekDay weekDay = DateJudgeUtils.dayForWeek(today);
    int preIdx = weekDay == WeekDay.MONDAY ? -3 : -1;
    return getByDay(today, preIdx);
  }

  /**
   * <p>获取昨天日期</p>
   *
   * @return 日期对象
   */
  public static Date preDay() {
    return getByToday(-1);
  }

  /**
   * <p>获取明天日期</p>
   *
   * @return 日期对象
   */
  public static Date nextDay() {
    return getByToday(1);
  }

  /**
   * <p>根据今日的日期获取日期</p>
   *
   * @param idx -1 表示获取前一天日期, -2 表示获取前两天, 1 表示获取后一天, 2 表示后两天, 以此类推
   * @return 日期对象
   */
  public static Date getByToday(int idx) {
    return getByDay(new Date(), idx);
  }

  /**
   * <p>根据基准日期获取日期</p>
   *
   * @param idx -1 表示获取前一天日期, -2 表示获取前两天, 1 表示获取后一天, 2 表示后两天, 以此类推
   * @return 日期对象
   */
  public static Date getByDay(Date date, int idx) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, idx);
    return calendar.getTime();
  }
}
