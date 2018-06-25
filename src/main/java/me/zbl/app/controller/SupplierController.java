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
package me.zbl.app.controller;

import me.zbl.app.domain.Supplier;
import me.zbl.app.service.SupplierService;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 供应商数据维护
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-17
 */
@Controller
public class SupplierController {

  @Autowired
  SupplierService supplierService;

  @GetMapping("/supplier/index")
  public String index() {
    return "app/data-maintenance/supplier/index";
  }

  @GetMapping("/supplier/add")
  public String add() {
    return "app/data-maintenance/supplier/add";
  }

  @GetMapping("/supplier/edit/{id}")
  public String edit(@PathVariable("id") String id, Model model) {
    Supplier find = supplierService.selectByPrimaryKey(id);
    model.addAttribute("supplier", find);
    return "app/data-maintenance/supplier/edit";
  }

  /**
   * 供应商列表
   */
  @GetMapping("/supplier/list")
  @ResponseBody
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<Supplier> list = supplierService.selectAllSupplier(query);
    return new PageWrapper(list, supplierService.count());
  }

  /**
   * 供应商保存
   */
  @PostMapping("/supplier/save")
  @ResponseBody
  public R save(Supplier supplier) {
    try {
      supplierService.insertSupplier(supplier);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  @PutMapping("/supplier/save")
  @ResponseBody
  public R saveUpdate(Supplier supplier) {
    try {
      supplierService.updateSupplier(supplier);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  @DeleteMapping("/supplier/remove/{id}")
  @ResponseBody
  public R remove(@PathVariable("id") String id) {
    try {
      supplierService.deleteSupplier(id);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }
}
