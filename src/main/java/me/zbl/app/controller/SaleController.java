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

import me.zbl.app.domain.BackFormDO;
import me.zbl.app.domain.DrugOutFormDO;
import me.zbl.app.domain.SaleDO;
import me.zbl.app.service.DrugInService;
import me.zbl.app.service.DrugOutService;
import me.zbl.common.controller.BaseController;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-23
 */
@Controller
public class SaleController extends BaseController {

  @Autowired
  private DrugOutService drugOutService;

  @Autowired
  DrugInService drugInService;

  @GetMapping("/sale/index")
  public String index() {
    return "app/sale/index";
  }

  @GetMapping("/sale/add")
  public String add() {
    return "app/sale/add";
  }

  @GetMapping("/sale/back")
  public String back() {
    return "app/sale/back";
  }

  @GetMapping("/sta/index")
  public String statistics() {
    return "app/statistics/index";
  }

  @GetMapping("/sale_detail/index")
  public String saleDetail() {
    return "app/sale-detail/index";
  }

  @GetMapping("/sale/list")
  @ResponseBody
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<SaleDO> list = drugOutService.saleList(query);
    return new PageWrapper(list, drugOutService.countSale());
  }

  @PostMapping("/sale/save")
  @ResponseBody
  public R saleSave(DrugOutFormDO drugOutFormDO) {
    drugOutFormDO.setManager(getUser().getName());
    try {
      drugOutService.saleSave(drugOutFormDO);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    //    检查库存下限
    drugOutService.checkLowerLimit();
    return R.ok();
  }

  @PostMapping("/sale/back")
  @ResponseBody
  public R saleBackSave(BackFormDO backFormDO) {
    backFormDO.setManager(getUser().getName());
    try {
      drugInService.back(backFormDO);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  @GetMapping("/sale/sta_sale_day")
  @ResponseBody
  public Object staSaleDay() {
    return drugOutService.staSaleDay();
  }

  @GetMapping("/sale/sta_sale_month")
  @ResponseBody
  public Object staSaleMonth() {
    return drugOutService.staSaleMonth();
  }

  @GetMapping("/sale/sta_sale_year")
  @ResponseBody
  public Object staSaleYear() {
    return drugOutService.staSaleYear();
  }
}
