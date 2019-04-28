package com.github.codinghck.base.util.common.base.uuid;

import java.util.UUID;

/**
 * @author hck
 * 2018/11/3 11:33 PM
 */
@SuppressWarnings({ "unused", "WeakerAccess", "SpellCheckingInspection" })
public class UUIDUtils {

  private UUIDUtils() {}

  /**
   * <p>得到指定数量的 UUID, 以字符串数组的形式返回</p>
   *
   * @param num 需要多少数量的 UUID
   * @return 指定数量 {@code num} 的 UUID 字符串数组
   */
  public static String[] getUUIDS(int num){
    if( num <= 0) {
      return null;
    }
    String[] uuidArr = new String[num];
    for (int i = 0; i < num; i++) {
      uuidArr[i] = getUUID32();
    }
    return uuidArr;
  }

  /**
   * <p>得到 32 位的 UUID, 以字符串数组的形式返回</p>
   *
   * @return 32位的 UUID 字符串
   */
  public static String getUUID32(){
    return UUID.randomUUID().toString().replace("-", "").toLowerCase();
  }
}
