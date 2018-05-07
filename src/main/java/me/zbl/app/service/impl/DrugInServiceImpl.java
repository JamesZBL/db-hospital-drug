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

import me.zbl.app.dao.InventoryMapper;
import me.zbl.app.domain.DrugInDO;
import me.zbl.app.domain.DrugInFormDO;
import me.zbl.app.service.DrugInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 药品入库业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-07
 */
@Service
public class DrugInServiceImpl implements DrugInService {

  @Autowired
  private InventoryMapper mapper;

  @Override
  public List<DrugInDO> list(Map<String, Object> params) {
    return mapper.list(params);
  }

  @Override
  public int count() {
    return mapper.count();
  }

  @Override
  public String dugInSave(DrugInFormDO drugInFormDO) {
    return mapper.drugInSave(drugInFormDO);
  }
}
