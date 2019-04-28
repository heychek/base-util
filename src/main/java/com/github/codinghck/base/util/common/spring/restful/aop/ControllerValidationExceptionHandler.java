package com.github.codinghck.base.util.common.spring.restful.aop;

import com.github.codinghck.base.util.common.base.str.StrConst;
import com.github.codinghck.base.util.common.spring.restful.response.RestResponse;
import com.github.codinghck.base.util.common.spring.restful.util.RestResHandler;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author hck 2019-04-12 17:32
 */
@Slf4j
@RestControllerAdvice
@SuppressWarnings("unused")
public class ControllerValidationExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Object methodArgumentNotValidException(
      HttpServletRequest request, MethodArgumentNotValidException e) {
      log.warn("Global ControllerValidationExceptionHandler doing...");
      RestResponse res = RestResHandler.getForParamErr(getErrMsg(e));
      res.setTip("path: " + request.getServletPath());
      return res;
  }

  private String getErrMsg(MethodArgumentNotValidException e) {
    BindingResult bind = e.getBindingResult();
    StringBuilder errMsg = new StringBuilder(bind.getFieldErrors().size() * 16);
    errMsg.append("param err:[");
    for (int i = 0; i < bind.getFieldErrors().size(); i++) {
      if (i > 0) {
        errMsg.append(StrConst.DOT_SEPARATOR).append(" ");
      }
      FieldError fieldError = bind.getFieldErrors().get(i);
      errMsg.append(fieldError.getField()).append(": ").append(fieldError.getDefaultMessage());
    }
    errMsg.append("]");
    return errMsg.toString();
  }
}
