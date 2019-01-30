package com.github.codinghck.base.util.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * 2018/10/29 3:46 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("WeakerAccess")
public class ErrData {
  private String dataId;
  private String msg;
}
