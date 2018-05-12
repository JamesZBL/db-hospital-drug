package me.zbl.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author 郑保乐
 */
public class PageWrapper implements Serializable {

  private static final long serialVersionUID = 1L;
  private int total;
  private List<?> rows;

  public PageWrapper(List<?> list, int total) {
    this.rows = list;
    this.total = total;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<?> getRows() {
    return rows;
  }

  public void setRows(List<?> rows) {
    this.rows = rows;
  }

}
