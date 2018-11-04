package com.github.hckisagoodboy.base.util.common.request;

import java.util.List;
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
public class ErrListOut {
  private String requestId;
  private List<ErrData> invalidData;
}
