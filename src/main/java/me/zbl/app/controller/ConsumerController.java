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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
