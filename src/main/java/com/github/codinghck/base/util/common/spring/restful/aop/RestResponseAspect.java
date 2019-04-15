package com.github.codinghck.base.util.common.spring.restful.aop;

import com.alibaba.fastjson.JSONObject;
import com.github.codinghck.base.util.common.spring.restful.exception.RestException;
import com.github.codinghck.base.util.common.spring.restful.response.RestResponse;
import com.github.codinghck.base.util.common.spring.restful.util.RestResHandler;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author hck 2019-04-10 17:34
 */
@Slf4j
@Aspect
@Component
@Order(11)
public class RestResponseAspect {

  @Pointcut("@annotation(com.github.codinghck.base.util.common.spring.restful.annotation.RestJsonResponse)")
  public void methodRestResponsePointCut() {
  }

  @Pointcut("@within(com.github.codinghck.base.util.common.spring.restful.annotation.RestJsonResponse)")
  public void classRestResponsePointCut() {
  }

  @Around("methodRestResponsePointCut() || classRestResponsePointCut()")
  public Object doAround(ProceedingJoinPoint jp) {
    HttpServletRequest request =
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    RestResponse res = proceed(jp, request);
    String requestId = request.getParameter("requestId");
    if (requestId != null) {
      res.setRequestId(requestId);
    }
    return JSONObject.toJSONString(res);
  }

  private RestResponse proceed(ProceedingJoinPoint jp, HttpServletRequest request) {
    RestResponse res;
    try {
      res = RestResHandler.getSuccess(jp.proceed());
    } catch (Throwable  e) {
      log.warn("rest exception handler, url: {}, e: {}, msg: {}, stackTrace: {}",
          request.getRequestURL().toString(), e, e.getMessage(), e.getStackTrace());
      res = RestResHandler.getForUnexpectErr(e.getMessage());
      res.setTip("path: " + request.getServletPath() + "; exception: " + e.getClass().getName());
      if (e instanceof RestException) {
        res.setCode(((RestException) e).getErrCode());
      }
    }
    return res;
  }
}
