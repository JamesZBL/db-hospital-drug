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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 药品入库列表
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-07
 */
public class SaleDO {

  //  订单编号
  private String orderId;
  //  原因备注
  private String comment;
  //  类型
  private String type;
  //  经办人
  private String manager;
  //  实时库存
  private int quantityNow;
  //  药品编号
  private String drugId;
  //  药品名称
  private String drugName;
  //  单价
  private BigDecimal price;
  //  供应商
  private String supplierName;
  //  规格
  private String specification;
  //  数量
  private Integer quantity;
  //  总金额
  private BigDecimal ammount;
  //  顾客姓名
  private String consumer;
  //  入库时间
  private Date gmtCreated;

  public String getDrugName() {
    return drugName;
  }

  public void setDrugName(String drugName) {
    this.drugName = drugName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getSupplierName() {
    return supplierName;
  }

  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName;
  }

  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getAmmount() {
    return ammount;
  }

  public void setAmmount(BigDecimal ammount) {
    this.ammount = ammount;
  }

  public Date getGmtCreated() {
    return gmtCreated;
  }

  public void setGmtCreated(Date gmtCreated) {
    this.gmtCreated = gmtCreated;
  }

  public String getDrugId() {
    return drugId;
  }

  public void setDrugId(String drugId) {
    this.drugId = drugId;
  }

  public int getQuantityNow() {
    return quantityNow;
  }

  public void setQuantityNow(int quantityNow) {
    this.quantityNow = quantityNow;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getConsumer() {
    return consumer;
  }

  public void setConsumer(String consumer) {
    this.consumer = consumer;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
}
