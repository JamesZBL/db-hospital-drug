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
package me.zbl.app.service.impl;

import com.github.pagehelper.Page;
import me.zbl.app.dao.DrugMapper;
import me.zbl.app.dao.InventoryMapper;
import me.zbl.app.domain.*;
import me.zbl.app.service.DrugOutService;
import me.zbl.oa.domain.NotifyDO;
import me.zbl.oa.service.NotifyService;
import me.zbl.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 药品出库业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-08
 */
@Service
public class DrugOutServiceImpl implements DrugOutService {

  @Autowired
  private DrugMapper drugMapper;

  @Autowired
  private InventoryMapper inventoryMapper;

  @Autowired
  private NotifyService notifyService;

  @Override
  public List<StaSaleDO> staSaleDay() {
    return inventoryMapper.staSaleDay();
  }

  @Override
  public List<StaSaleDO> staSaleMonth() {
    return inventoryMapper.staSaleMonth();
  }

  @Override
  public List<StaSaleDO> staSaleYear() {
    return inventoryMapper.staSaleYear();
  }

  @Override
  public List<DrugOutDO> list(Map<String, Object> params) {
    return inventoryMapper.outList(params);
  }

  @Override
  public Page<SaleDO> saleList(Map<String, Object> params) {
    return PageUtil.page(params, () -> inventoryMapper.saleList(params));
  }

  @Override
  public int count() {
    return inventoryMapper.countOut();
  }

  @Override
  public int countSale() {
    return inventoryMapper.countSale();
  }

  @Transactional
  @Override
  public int drugOutSave(DrugOutFormDO drugOutFormDO) {
    Optional.ofNullable(drugMapper.selectByPrimaryKey(drugOutFormDO.getDrugId())).
            orElseThrow(() -> new IllegalArgumentException("输入的药品编号不存在"));
    Map<String, Object> params = new HashMap<>();
    String drugId = drugOutFormDO.getDrugId();
    params.put("drugId", drugId);
    params.put("quantity", 0 - drugOutFormDO.getQuantity());
    // 更新药品的库存
    drugMapper.increaseAndDecreaseQuantity(params);
    Drug post = drugMapper.selectByPrimaryKey(drugId);
    if (post.getQuantity() < 0) {
      throw new IllegalArgumentException("库存不足！");
    }
    if (StringUtils.isEmpty(drugOutFormDO.getComment())) {
      drugOutFormDO.setComment("退回供应商出库");
    }
    // 保存仓储变动记录
    return inventoryMapper.drugOutSave(drugOutFormDO);
  }

  @Override
  public int saleSave(DrugOutFormDO drugOutFormDO) {
    Optional.ofNullable(drugMapper.selectByPrimaryKey(drugOutFormDO.getDrugId())).
            orElseThrow(() -> new IllegalArgumentException("输入的药品编号不存在"));
    Map<String, Object> params = new HashMap<>();
    String drugId = drugOutFormDO.getDrugId();
    params.put("drugId", drugId);
    params.put("quantity", 0 - drugOutFormDO.getQuantity());
    // 更新药品的库存
    drugMapper.increaseAndDecreaseQuantity(params);
    Drug post = drugMapper.selectByPrimaryKey(drugId);
    if (post.getQuantity() < 0) {
      throw new IllegalArgumentException("库存不足！");
    }
    BigDecimal price = post.getPrice();
    int quantity = drugOutFormDO.getQuantity();
    float ammount = price.floatValue() * (float) quantity;
    drugOutFormDO.setAmmount(ammount);
    if (StringUtils.isEmpty(drugOutFormDO.getComment())) {
      drugOutFormDO.setComment("销售出库");
    }
    // 保存仓储变动记录
    return inventoryMapper.saleSave(drugOutFormDO);
  }

  @Override
  public void checkLowerLimit() {
    List<Drug> drugs = drugMapper.selectOverLowerLimit();
    drugs.forEach(d -> {
      String title = "有药品的库存低于预定下限值";
      //      药品编号
      String drugId = d.getId();
      //      药名
      String name = d.getName();
      //      消息内容
      String content = "有药品的库存低于预定下限值，请及时进货！药品编号：" + drugId + "，药名：" + name + "";

      NotifyDO notify = new NotifyDO();
      notify.setTitle(title);
      notify.setStatus("1");
      notify.setContent(content);
      notify.setType("5");
      notify.setCreateBy((long) 1);
      notify.setUserIds(new Long[]{(long) 140});
      //     发送消息
      notifyService.save(notify);
    });
  }
}
