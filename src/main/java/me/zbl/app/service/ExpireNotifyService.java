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
package me.zbl.app.service;

/**
 * 药品过期提醒业务接口
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-05-12
 */
public interface ExpireNotifyService {

  /**
   * 向仓库管理员角色的用户发送药品过期提醒
   */
  void notifyDrugsExpiredToday();
}
