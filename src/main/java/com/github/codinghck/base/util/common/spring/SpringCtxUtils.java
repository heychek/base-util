package com.github.codinghck.base.util.common.spring;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author hck 2018/11/21 1:52 PM
 */
@Service
public class SpringCtxUtils implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(@NotNull ApplicationContext arg0)
      throws BeansException {
    applicationContext = arg0;
  }

  /**
   * <p>获取 applicationContext 对象</p>
   *
   * @return applicationContext 对象
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * <p>根据 bean 的 id 来查找对象</p>
   *
   * @param id bean 的 id
   * @return bean 对象
   */
  public static Object getBeanById(String id) {
    System.out.println("applicationContext: " + applicationContext);
    return applicationContext.getBean(id);
  }

  /**
   * <p>根据 bean 的 class 来查找对象</p>
   *
   * @param c bean 的 class
   * @param <T> 对象类型
   * @return bean 对象
   */
  public static <T> T getBeanByClass(Class<T> c) {
    return applicationContext.getBean(c);
  }

  /**
   * <p>根据 bean 的 class 来查找所有的对象(包括子类)</p>
   *
   * @param c bean 的 class
   * @param <T> 对象类型
   * @return 所有的 bean 对象(包括子类)
   */
  public static <T> Map<String, T> getBeansByClass(Class<T> c) {
    return applicationContext.getBeansOfType(c);
  }
}