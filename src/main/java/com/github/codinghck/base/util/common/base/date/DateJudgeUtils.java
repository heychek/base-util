package com.github.codinghck.base.util.common.base.date;

import java.util.Calendar;
import java.util.Date;

/**
 * @author hck 2019-03-08 16:20
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DateJudgeUtils {

  private DateJudgeUtils() {}

  /**
   * <p>判断是否是工作日</p>
   *
   * @return 是否是工作日
   */
  public static boolean isWeek() {
    return isWeek(new Date());
  }

  /**
   * <p>判断是否是工作日</p>
   *
   * @param date 日期
   * @return 是否是工作日
   */
  public static boolean isWeek(Date date) {
    WeekDay weekDay = dayForWeek(date);
    return isWeek(weekDay);
  }

  /**
   * <p>判断是否是周末</p>
   *
   * @return 是否是周末
   */
  public static boolean isWeekend() {
    WeekDay weekDay = dayForWeek(new Date());
    return isWeekend(weekDay);
  }

  /**
   * <p>判断是否是周末</p>
   *
   * @param date 日期
   * @return 是否是周末
   */
  public static boolean isWeekend(Date date) {
    WeekDay weekDay = dayForWeek(date);
    return isWeekend(weekDay);
  }

  /**
   * <p>判断是否是工作日</p>
   *
   * @param weekDay 今天周几的枚举
   * @return 是否是工作日
   */
  public static boolean isWeek(WeekDay weekDay) {
    return !isWeekend(weekDay);
  }

  /**
   * <p>判断是否是周末</p>
   *
   * @param weekDay 今天周几的枚举
   * @return 是否是周末
   */
  public static boolean isWeekend(WeekDay weekDay) {
    return weekDay == WeekDay.SATURDAY
        || weekDay == WeekDay.SUNDAY;
  }

  /**
   * <p>根据日期返回今天周几的枚举</p>
   *
   * @param date 日期
   * @return 星期几的枚举
   */
  public static WeekDay dayForWeek(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

    for (WeekDay weakDay : WeekDay.values()) {
      if (weakDay.getVal() == (w < 0 ? 0 : w)) {
        return weakDay;
      }
    }
    return WeekDay.SUNDAY;
  }
}
