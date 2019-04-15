package com.github.codinghck.base.util.common.spring.restful.aop;

import com.alibaba.fastjson.JSONObject;
import com.github.codinghck.base.util.common.spring.restful.util.JoinPointUtils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 日志的 order 需要最小，其次异常，其次校验
 * @author hck 2019-04-10 17:34
 */
@Aspect
@Component
@Slf4j
@Order(10)
public class LogAroundAspect {

  @Pointcut("@annotation(com.github.codinghck.base.util.common.spring.restful.annotation.LogAround)")
  public void methodLogAroundPointCut() {
  }

  @Pointcut("@within(com.github.codinghck.base.util.common.spring.restful.annotation.LogAround)")
  public void classLogAroundPointCut() {
  }

  @Around("methodLogAroundPointCut() || classLogAroundPointCut()")
  public Object doAround(ProceedingJoinPoint jp) throws Throwable {
    Map<String, String> params = JoinPointUtils.getParams(jp);
    log.info("请求参数: {}", JSONObject.toJSONString(params));
    Object result = jp.proceed();
    log.info("请求结果: {}", result);
    return result;
  }
}
