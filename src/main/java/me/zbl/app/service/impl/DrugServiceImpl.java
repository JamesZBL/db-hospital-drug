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
import me.zbl.app.dao.SupplierMapper;
import me.zbl.app.domain.Drug;
import me.zbl.app.domain.DrugDO;
import me.zbl.app.domain.Supplier;
import me.zbl.app.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 药品管理业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-19
 */
@Service
public class DrugServiceImpl implements DrugService {

  @Autowired
  private DrugMapper drugMapper;

  @Autowired
  private SupplierMapper supplierMapper;

  @Override
  public Drug selectDrugByPrimaryKey(String id) {
    return drugMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<DrugDO> selectAllDrug(Map<String, Object> params) {
    return drugMapper.selectAllDrug(params);
  }

  @Override
  public int count() {
    return drugMapper.count();
  }

  @Override
  public int insertDrug(Drug drug) {
    Drug find = drugMapper.selectByPrimaryKey(drug.getId());
    if (null != find) {
      throw new IllegalArgumentException("药品编号已经存在");
    }
    Optional<Supplier> supplier = Optional.ofNullable(supplierMapper.
            selectByPrimaryKey(drug.getSupplierId()));
    supplier.orElseThrow(() -> new IllegalArgumentException("供应商编号不存在"));
    return drugMapper.insertSelective(drug);
  }

  @Override
  public int updateDrug(Drug drug) {
    return drugMapper.updateByPrimaryKeySelective(drug);
  }

  @Override
  public int deleteDrug(String id) {
    return drugMapper.deleteByPrimaryKey(id);
  }
}
