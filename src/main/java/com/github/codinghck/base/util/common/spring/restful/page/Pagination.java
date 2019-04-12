package com.github.codinghck.base.util.common.spring.restful.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hck 2019-04-08 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
  private Long pageSize;
  private Long currentPage;
  private Long totalPages;
  private Long totalElement;
}
