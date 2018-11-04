package com.github.hckisagoodboy.base.util.common.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @author hck
 * @date 2018/11/3 11:45 PM
 */
public class JsonUtils {
  private JsonUtils() {}

  /**
   * <p>将一个 <code>T</code> 类型对象转换为 <code>JSONObject</code> 对象</p>
   * @param obj 需要被转换的原始对象
   * @param <T> 该原始对象的泛型
   * @return 转换后的 <code>JSONObject</code> 对象
   */
  public static <T> JSONObject objToJsonObj(T obj) {
    String jsonStr = JSONObject.toJSONString(obj);
    return JSONObject.parseObject(jsonStr);
  }
}
