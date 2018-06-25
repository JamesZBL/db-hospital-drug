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

import me.zbl.app.dao.SupplierMapper;
import me.zbl.app.domain.Supplier;
import me.zbl.app.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-17
 */
@Service
public class SupplierServiceImpl implements SupplierService {

  @Autowired
  SupplierMapper supplierMapper;

  @Override
  public Supplier selectByPrimaryKey(String id) {
    return supplierMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<Supplier> selectAllSupplier(Map<String, Object> params) {
    return supplierMapper.selectAllSupplier(params);
  }

  @Override
  public int count() {
    return supplierMapper.count();
  }

  @Override
  public int insertSupplier(Supplier supplier) {
    Supplier virtual = supplierMapper.selectByPrimaryKey(supplier.getId());
    if (null != virtual) {
      throw new IllegalArgumentException("该编号已经存在");
    }
    return supplierMapper.insertSelective(supplier);
  }

  @Override
  public int deleteSupplier(String id) {
    return supplierMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateSupplier(Supplier supplier) {
    return supplierMapper.updateByPrimaryKeySelective(supplier);
  }
}
