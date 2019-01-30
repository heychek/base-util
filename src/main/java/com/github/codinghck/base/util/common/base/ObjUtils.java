package com.github.codinghck.base.util.common.base;

import com.alibaba.fastjson.JSONObject;
import com.github.codinghck.base.util.common.lambda.BooleanExecutor;

/**
 * @author hck
 * 2018/11/3 11:50 PM
 */
public class ObjUtils {
  private ObjUtils() {}
  private static final String EMPTY_OBJ_STR = "{}";

  /**
   * <p>判断传进来的 {@code obj} 是否为 null</p>
   *
   * @param obj 需要判断的 <code>Object</code> 对象
   * @return 判断是否为 null 的布尔值
   */
  public static boolean isNull(Object obj) {
    return null == obj;
  }

  /**
   * <p>判断传进来的 {@code obj} 是否不为 null</p>
   *
   * @param obj 需要判断的 <code>Object</code> 对象
   * @return 判断是否不为 null 的布尔值
   */
  public static boolean isNotNull(Object obj) {
    return null != obj;
  }

  /**
   * <p>判断传进来的 {@code obj} 是否为 null 或为空对象</p>
   *
   * @param obj 需要判断的 <code>Object</code> 对象
   * @return 判断是否为 null 或为空对象的布尔值
   */
  public static boolean isEmpty(Object obj) {
    return isNull(obj) || EMPTY_OBJ_STR.equals(JSONObject.toJSONString(obj));
  }

  /**
   * <p>判断传进来的所有对象是否含有为 null 的对象</p>
   *
   * @param objs 需要判断的所有 <code>Object</code> 对象
   * @return 判断是否含有为 null 的对象的布尔值
   */
  public static boolean hasNull(Object... objs) {
    return hasInValidElement(ObjUtils::isNotNull, objs);
  }

  /**
   * <p>判断传进来的所有对象是否含有为 null 或为空对象的对象</p>
   *
   * @param objs 需要判断的所有 <code>Object</code> 对象
   * @return 判断是否含有为 null 或为空对象的布尔值
   */
  public static boolean hasObjsEmpty(Object... objs) {
    return hasInValidElement(ObjUtils::isEmpty, objs);
  }

  /**
   * <p>对每一个传入的参数进行判断, {@code executor} 是传入的条件。即判断是否所有
   * <p>传入的参数均符合 {@code executor} 的条件
   * <p>关于 <code>@SafeVarargs</code> 注释, 这是因为可变长度的方法参数的实际值是通过数组来传递的,
   * <p>而数组中存储的是不可具体化的泛型类对象，自身存在类型安全问题。因此编译器会给出相应的
   * <p>警告消息, <code>@SafeVarargs</code> 注释用以消除该警告
   *
   * @param executor 传入的判断方法, 该方法返回布尔值, 即被判断的参数是否满足条件
   * @param args 需要被逐一判断的参数列表
   * @param <T> 参数类型
   * @return 如果传入的参数有一个或以上不满足条件 {@code executor}, 则返回 false, 全部满足条件则返回 true
   */
  @SafeVarargs
  public static <T> boolean hasInValidElement(BooleanExecutor<T> executor, T... args) {
    for (T t : args) {
      if (!executor.execute(t)) {
        return true;
      }
    }
    return false;
  }
}
