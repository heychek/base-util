package com.github.codinghck.base.util.common.spring.restful.page;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hck 2019-04-08 16:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableRes<T> {
  private Pagination pagination;
  private List<T> data;
}
