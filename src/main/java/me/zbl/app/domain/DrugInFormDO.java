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

import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * 入库登记表单
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-07
 */
public class DrugInFormDO {

  private String id;

  //  生产日期
  @Transient
  private Date madeDate;
  //  药品 id
  private String drugId;
  //  入库数量
  private int quantity;
  //  经办人
  private String manager;
  //  总金额
  private float ammount;

  public String getDrugId() {
    return drugId;
  }

  public void setDrugId(String drugId) {
    this.drugId = drugId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public float getAmmount() {
    return ammount;
  }

  public void setAmmount(float ammount) {
    this.ammount = ammount;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getMadeDate() {
    return madeDate;
  }

  public void setMadeDate(Date madeDate) {
    this.madeDate = madeDate;
  }
}
