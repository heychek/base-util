package com.github.codinghck.base.util.common.base.date;

import com.github.codinghck.base.util.common.base.str.StrConst;
import java.text.SimpleDateFormat;
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
}
