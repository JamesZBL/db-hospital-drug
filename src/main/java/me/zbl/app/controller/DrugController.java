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

import me.zbl.app.domain.Drug;
import me.zbl.app.domain.DrugDO;
import me.zbl.app.service.DrugService;
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
 * @date 2018-06-19
 */
@Controller
public class DrugController {

  @Autowired
  DrugService drugService;

  @GetMapping("/drug/index")
  public String index(){
    return "/app/data-maintenance/drug/index";
  }

  @GetMapping("/drug/list")
  @ResponseBody
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<DrugDO> drugs = drugService.selectAllDrug(query);
    return new PageWrapper(drugs, drugService.count());
  }

  @PostMapping("/drug/save")
  @ResponseBody
  public R save(Drug drug) {
    try {
      drugService.insertDrug(drug);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }
}
