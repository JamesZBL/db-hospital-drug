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
package me.zbl.app.task;

import me.zbl.app.service.DrugOutService;
import me.zbl.app.service.ExpireNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * <p>
 * 1. 药品过期检查
 * 2. 药品库存检查
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-12
 */
@Component
public class InventoryCheckTask {

  private static final Logger log = LoggerFactory.getLogger(InventoryCheckTask.class);

  @Autowired
  private ExpireNotifyService expireNotifyService;

  @Autowired
  private DrugOutService drugOutService;

  /**
   * 检查药品过期
   * 每天 00:00 执行
   */
  @Scheduled(cron = "0 0 0  * * ?")
  public void checkExpire() {
    expireNotifyService.notifyDrugsExpiredToday();
    log.info("定时任务：发送药品过期提醒");
  }

  /**
   * 检查药品库存
   * 每天 00:00 执行
   */
  @Scheduled(cron = "0 0 0  * * ?")
  public void checkLowerLimit() {
    drugOutService.checkLowerLimit();
    log.info("定时任务：发送药品库存提醒");
  }
}
