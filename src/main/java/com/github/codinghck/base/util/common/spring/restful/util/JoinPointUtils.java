package com.github.codinghck.base.util.common.spring.restful.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author hck 2019-04-10 23:46
 */
@SuppressWarnings("unused")
public class JoinPointUtils {

  private JoinPointUtils() {
  }

  /**
   * <p>根据参数名从切面对象中获取 参数值</p>
   *
   * @param jp 切点对象
   * @param pName 参数名
   * @return 如果找不到则返回 null
   */
  public static String getParam(ProceedingJoinPoint jp, String pName) {
    if (jp == null || pName == null) {
      return null;
    }
    MethodSignature s = (MethodSignature) jp.getSignature();
    String[] pNames = s.getParameterNames();
    Object[] args = jp.getArgs();
    if (pNames != null && pNames.length > 0) {
      for (int i = 0; i < pNames.length; i++) {
        String name = pNames[i];
        if (pName.equals(name)) {
          return args[i].toString();
        }
      }
    }
    return null;
  }

  /**
   * <p>获取切点的 HttpServletRequest 对象</p>
   *
   * @param jp 切点对象
   * @return HttpServletRequest 对象
   */
  private HttpServletRequest getServletRequest(JoinPoint jp) {
    if (jp == null || jp.getSignature() == null) {
      return null;
    }

    MethodSignature s = (MethodSignature) jp.getSignature();
    Object[] args = jp.getArgs();
    Class<?>[] pTypes = s.getParameterTypes();
    if (pTypes == null || pTypes.length <= 0) {
      return null;
    }

    for (int i = 0; i < pTypes.length; i++) {
      Class<?> pType = pTypes[i];
      if (pType == HttpServletRequest.class) {
        return (HttpServletRequest) args[i];
      }
    }
    return null;
  }

  /**
   * <p>获取切点参数对象</p>
   *
   * @param jp 切点对象
   * @return 参数对象
   */
  public static Map<String, String> getParams(JoinPoint jp) {
    Map<String, String> res = new HashMap<>();
    Object[] paramArgs = jp.getArgs();
    String[] paramNames = ((CodeSignature) jp.getSignature()).getParameterNames();
    for (int i = 0, len = paramArgs.length; i < len; i++) {
      res.put(paramNames[i], paramArgs[i].toString());
    }
    return res;
  }

  /**
   * <p>获取切点注解</p>
   * @param jp 切点对象
   * @return 注解数组
   */
  public static Annotation[][] getAnnotations(JoinPoint jp) {
    MethodSignature signature = (MethodSignature) jp.getSignature();
    Method method = signature.getMethod();
    return method.getParameterAnnotations();
  }
}
