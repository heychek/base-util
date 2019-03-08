package com.github.codinghck.base.util.common.base.date;

/**
 * @author hck 2019-03-08 16:13
 */
@SuppressWarnings("unused")
public enum WeekDay {

  /**
   * 参考 desc 属性
   */
  MONDAY(1, "周一"),
  TUESDAY(2, "周二"),
  WEDNESDAY(3, "周三"),
  THURSDAY(4, "周四"),
  FRIDAY(5, "周五"),
  SATURDAY(6, "周六"),
  SUNDAY(0, "周日");

  WeekDay(int val, String desc) {
    this.val = val;
    this.desc = desc;
  }

  private int val;
  private String desc;

  public int getVal() {
    return val;
  }

  public String getDesc() {
    return desc;
  }
}
