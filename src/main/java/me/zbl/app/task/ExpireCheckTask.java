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

import me.zbl.app.service.ExpireNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 药品过期检查定时任务
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-12
 */
@Component
public class ExpireCheckTask {

  private static final Logger log = LoggerFactory.getLogger(ExpireCheckTask.class);

  @Autowired
  private ExpireNotifyService expireNotifyService;

  /**
   * 每天 00:00 执行
   */
  @Scheduled(cron = "0 0 0  * * ?")
  public void sayHello(){
    expireNotifyService.notifyDrugsExpiredToday();
    log.info("定时任务：发送药品过期提醒");
  }
}
