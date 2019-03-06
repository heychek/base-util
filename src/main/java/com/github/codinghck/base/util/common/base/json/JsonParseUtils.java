package com.github.codinghck.base.util.common.base.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author hck
 * 2018/11/3 11:45 PM
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class JsonParseUtils {

  private JsonParseUtils() {}

  /**
   * <p>将一个 <code>T</code> 类型对象转换为 <code>JSONObject</code> 对象</p>
   *
   * @param obj 需要被转换的原始对象
   * @param <T> 该原始对象的泛型
   * @return 转换后的 <code>JSONObject</code> 对象
   */
  public static <T> JSONObject obj2JsonObj(T obj) {
    String jsonStr = obj2JsonStr(obj);
    return JSONObject.parseObject(jsonStr);
  }

  /**
   * <p>将一个 <code>T</code> 类型对象转换为 json 字符串</p>
   *
   * @param obj 需要被转换的原始对象
   * @param <T> 该原始对象的泛型
   * @return 转换后的 json 字符串
   */
  public static <T> String obj2JsonStr(T obj) {
    String toStr = obj.toString();
    try {
      JSONObject.parseObject(toStr);
      return toStr;
    } catch (Exception e) {
      return JSON.toJSONString(obj);
    }
  }
}
