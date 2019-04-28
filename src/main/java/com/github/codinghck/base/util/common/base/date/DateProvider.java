package com.github.codinghck.base.util.common.base.date;

import com.github.codinghck.base.util.common.base.str.StrConst;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hck 2019-01-30 22:16
 */
@SuppressWarnings({"unused", "WeakerAccess"})
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
    int preIdx = -1;
    switch (weekDay) {
      case MONDAY:
        preIdx = -3;
        break;
      case SUNDAY:
        preIdx = -2;
        break;
      default:
        break;
    }
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
   * @param date 基准日期
   * @param idx -1 表示获取前一天日期, -2 表示获取前两天, 1 表示获取后一天, 2 表示后两天, 以此类推
   * @return 日期对象
   */
  public static Date getByDay(Date date, int idx) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DAY_OF_MONTH, idx);
    return calendar.getTime();
  }

  /**
   * <p>获取上个月日期
   * <p>如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果当天是 2019-03-31 12:00:00, 返回值为 2019-02-28 12:00:00
   *
   * @return 日期对象
   */
  public static Date preMonth() {
    return getByBaseDayForMonthStep(-1);
  }

  /**
   * <p>获取下个月日期
   * <p>如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果当天是 2019-01-31 12:00:00, 返回值为 2019-02-28 12:00:00
   *
   * @return 日期对象
   */
  public static Date nextMonth() {
    return getByBaseDayForMonthStep(1);
  }

  /**
   * <p>获取上个月日期
   * <p>如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果当天是 2019-03-31 12:00:00, 返回值为 2019-02-28 12:00:00
   *
   * @param date 基准日期
   * @return 日期对象
   */
  public static Date preMonth(Date date) {
    return getByBaseDayForMonthStep(date,-1);
  }

  /**
   * <p>获取下个月日期
   * <p>如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果当天是 2019-01-31 12:00:00, 返回值为 2019-02-28 12:00:00
   *
   * @param date 基准日期
   * @return 日期对象
   */
  public static Date nextMonth(Date date) {
    return getByBaseDayForMonthStep(date,1);
  }

  /**
   * <p>根据当前日期获取日期</p>
   * <p>如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果当天是 2019-01-31 12:00:00, idx 为 1, 返回值为 2019-02-28 12:00:00
   *
   * @param idx -1 表示获取前一个月的日期, -2 表示获取前两个月, 1 表示获取后一个月, 2 表示后两个月, 以此类推
   * @return 日期对象
   */
  public static Date getByBaseDayForMonthStep(int idx) {
    return getByBaseDayForMonthStep(new Date(), idx);
  }

  /**
   * <p>根据基准日期获取日期
   * <p>注意: 比如, 如果传入的是 2019-01-31 12:00:00 和 1, 返回值为 2019-02-28 12:00:00
   *
   * @param date 基准日期
   * @param idx -1 表示获取前一个月的日期, -2 表示获取前两个月, 1 表示获取后一个月, 2 表示后两个月, 以此类推
   * @return 日期对象
   */
  public static Date getByBaseDayForMonthStep(Date date, int idx) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int initDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    Date firstDayOfMonthDate = getFirstDayOfMonth(date);
    calendar.setTime(firstDayOfMonthDate);
    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + idx);
    int currMaxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    calendar.set(Calendar.DAY_OF_MONTH,
        initDayOfMonth > currMaxDayOfMonth ? currMaxDayOfMonth : initDayOfMonth);
    return calendar.getTime();
  }

  /**
   * <p>根据当前日期获取日期
   * <p>注意: 如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果传入的是 2019-01-03 12:00:00 和 32, 返回值为 2019-01-31 12:00:00
   *
   * @param dayOfMonth 表示当月日期，比如 1 表示当月 1 号, 2 表示当月 2 号, 以此类推
   * @return 日期对象
   */
  public static Date getByBaseDayForDayOfMonth(int dayOfMonth) {
    return getByBaseDayForDayOfMonth(new Date(), dayOfMonth);
  }

  /**
   * <p>根据基准日期获取日期
   * <p>注意: 如果该月没有该日期, 则会选择该月的最后一天
   * <p>比如, 如果传入的是 2019-01-03 12:00:00 和 32, 返回值为 2019-01-31 12:00:00
   *
   * @param date 基准日期
   * @param dayOfMonth 表示当月日期，比如 1 表示当月 1 号, 2 表示当月 2 号, 以此类推
   * @return 日期对象
   */
  public static Date getByBaseDayForDayOfMonth(Date date, int dayOfMonth) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int currMaxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    calendar.set(Calendar.DAY_OF_MONTH,
        dayOfMonth > currMaxDayOfMonth ? currMaxDayOfMonth : dayOfMonth);
    return calendar.getTime();
  }

  /**
   * <p>获取当前月份最后一天的日期</p>
   *
   * @param date 基准日期
   * @return 基于基准日期时间的当月最后一天的日期
   */
  public static Date getLastDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
    return calendar.getTime();
  }

  /**
   * <p>获取当前月份第一天的日期</p>
   *
   * @param date 基准日期
   * @return 基于基准日期时间的当月第一天的日期
   */
  public static Date getFirstDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
    return calendar.getTime();
  }

  /**
   * <p>获取基于基准日期的下个月的第一天</p>
   *
   * @param date 基准日期
   * @return 下个月的第一天的日期
   */
  public static Date getFirstDayOfNextMonth(Date date) {
    Date firstDay = getFirstDayOfMonth(date);
    return nextMonth(firstDay);
  }

  /**
   * <p>获取基于基准日期的上个月最后一天</p>
   *
   * @param date 基准日期
   * @return 上个月最后一天的日期
   */
  public static Date getLastDayOfPreMonth(Date date) {
    Date preMonth = preMonth(date);
    return getLastDayOfMonth(date);
  }
}
