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
package me.zbl.app.service;

import com.github.pagehelper.Page;
import me.zbl.app.domain.DrugOutDO;
import me.zbl.app.domain.DrugOutFormDO;
import me.zbl.app.domain.SaleDO;
import me.zbl.app.domain.StaSaleDO;

import java.util.List;
import java.util.Map;

/**
 * 药品出库业务接口
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-08
 */
public interface DrugOutService {

  List<StaSaleDO> staSaleDay();

  List<StaSaleDO> staSaleMonth();

  List<StaSaleDO> staSaleYear();

  List<DrugOutDO> list(Map<String, Object> params);

  Page<SaleDO> saleList(Map<String, Object> params);

  int count();

  int countSale();

  int drugOutSave(DrugOutFormDO drugOutFormDO) throws IllegalArgumentException;

  int saleSave(DrugOutFormDO drugOutFormDO);

  /**
   * 检查库存下限
   */
  void checkLowerLimit();
}
