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

import me.zbl.app.domain.Consumer;
import me.zbl.app.service.ConsumerService;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 顾客管理
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-19
 */
@Controller
public class ConsumerController {

  @Autowired
  private ConsumerService consumerService;

  @GetMapping("/consumer/index")
  public String index() {
    return "app/data-maintenance/consumer/index";
  }

  @GetMapping("/consumer/add")
  public String add() {
    return "app/data-maintenance/consumer/add";
  }

  @GetMapping("/consumer/edit/{id}")
  public String edit(@PathVariable("id") String id, Model model) {
    Consumer find = consumerService.selectByPrimaryKey(id);
    model.addAttribute("consumer", find);
    return "app/data-maintenance/consumer/edit";
  }

  /**
   * 顾客列表
   */
  @GetMapping("/consumer/list")
  @ResponseBody
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<Consumer> consumers = consumerService.selectAllConsumer(query);
    return new PageWrapper(consumers, consumerService.count());
  }

  /**
   * 顾客保存
   */
  @PostMapping("/consumer/save")
  @ResponseBody
  public R save(Consumer consumer) {
    try {
      consumerService.insertConsumer(consumer);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  /**
   * 顾客更新
   */
  @PutMapping("/consumer/save")
  @ResponseBody
  public R saveUpdate(Consumer consumer) {
    try {
      consumerService.updateConsumer(consumer);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  /**
   * 顾客删除
   */
  @DeleteMapping("/consumer/remove/{id}")
  @ResponseBody
  public R remove(@PathVariable("id") String id) {
    try {
      consumerService.deleteConsumer(id);
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(1, e.getMessage());
    }
    return R.ok();
  }

  /**
   * 下拉选择顾客
   */
  @GetMapping("/consumer/search")
  @ResponseBody
  public Map<String, Object> search(@RequestParam("q") String name) {
    Map<String, Object> entity = new HashMap<>();
    List<Consumer> consumers = consumerService.searchByName(name);
    List<Map<String, Object>> results = new ArrayList<>();
    consumers.forEach(c -> {
      Map<String, Object> item = new HashMap<>();
      item.put("id", c.getId());
      item.put("text", c.getName() + '-' + c.getTel());
      results.add(item);
    });
    entity.put("results", results);
    return entity;
  }
}
