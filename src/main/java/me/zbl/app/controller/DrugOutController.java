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

import me.zbl.app.domain.DrugOutDO;
import me.zbl.app.domain.DrugOutFormDO;
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
 * 药品出库
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-08
 */
@Controller
public class DrugOutController extends BaseController {

  @Autowired
  private DrugOutService service;

  /**
   * 药品出库页面
   */
  @GetMapping("/inventory/drugout")
  public String drugOutPage() {
    return "app/inventory/drug-out/drug-out";
  }

  /**
   * 药品出库登记
   */
  @GetMapping("/inventory/out")
  public String drugOutAddPage() {
    return "app/inventory/drug-out/out";
  }

  /**
   * 出库记录列表
   *
   * @param params 查询参数
   */
  @ResponseBody
  @GetMapping("/inventory/listout")
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<DrugOutDO> list = service.list(query);
    return new PageWrapper(list, service.count());
  }

  /**
   * 出库记录保存
   *
   * @param params 参数
   */
  @ResponseBody
  @PostMapping("/inventory/drugout/save")
  public R save(DrugOutFormDO params) {
    //    经办人姓名取当前用户的用户名
    String username = getUser().getName();
    params.setManager(username);
    //    保存出库记录
    try {
      service.drugOutSave(params);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    //    检查库存下限
    service.checkLowerLimit();
    return R.ok();
  }
}
