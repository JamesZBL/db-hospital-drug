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
import me.zbl.app.domain.Drug;
import me.zbl.app.service.ExpireNotifyService;
import me.zbl.oa.domain.NotifyDO;
import me.zbl.oa.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 药品过期提醒业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-12
 */
@Service
public class ExpireNotifyServiceImpl implements ExpireNotifyService {

  @Autowired
  private DrugMapper drugMapper;

  @Autowired
  NotifyService notifyService;

  @Override
  public void notifyDrugsExpiredToday() {
    List<Drug> drugs = drugMapper.selectByExpireDate(new Date());
    drugs.forEach(d -> {
      String title = "有药品将要在今天过期";
      //      药品编号
      String drugId = d.getId();
      //      药名
      String name = d.getName();
      //      消息内容
      String content = "有一批药品将于今天过期，如有剩余存货请及时处理！药品编号：" + drugId + "，药名：" + name + "";

      NotifyDO notify = new NotifyDO();
      notify.setTitle(title);
      notify.setStatus("1");
      notify.setContent(content);
      notify.setType("4");
      notify.setCreateBy((long) 1);
      notify.setUserIds(new Long[]{(long) 140});
      //     发送消息
      notifyService.save(notify);
    });
  }
}
