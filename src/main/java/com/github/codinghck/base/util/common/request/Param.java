package com.github.codinghck.base.util.common.request;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hck
 * 2018/10/30 1:34 PM
 */
@Data
public class Param<T> {
  @NotBlank
  @Size(max = 32)
  private String requestId;
  @Valid
  private T data;
}
