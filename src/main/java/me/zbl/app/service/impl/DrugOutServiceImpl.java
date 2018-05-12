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

import me.zbl.app.dao.DrugMapper;
import me.zbl.app.dao.InventoryMapper;
import me.zbl.app.domain.Drug;
import me.zbl.app.domain.DrugOutDO;
import me.zbl.app.domain.DrugOutFormDO;
import me.zbl.app.service.DrugOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

  @Override
  public List<DrugOutDO> list(Map<String, Object> params) {
    return inventoryMapper.outList(params);
  }

  @Override
  public int count() {
    return inventoryMapper.countOut();
  }

  @Transactional
  @Override
  public int drugOutSave(DrugOutFormDO drugOutFormDO) {
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
    // 保存仓储变动记录
    return inventoryMapper.drugOutSave(drugOutFormDO);
  }
}
