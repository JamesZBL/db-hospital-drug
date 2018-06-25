/*
 * Copyright 2018 JamesZBL
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package me.zbl.app.domain;

/**
 * 退货表单
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-24
 */
public class BackFormDO {

  //  流水号
  private String orderId;
  //  药品是否有问题
  private boolean hasProblem;
  //  退货操作人
  private String manager;
  //  原因备注
  private String comment;

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public boolean isHasProblem() {
    return hasProblem;
  }

  public void setHasProblem(boolean hasProblem) {
    this.hasProblem = hasProblem;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
