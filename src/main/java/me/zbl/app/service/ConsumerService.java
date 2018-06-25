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

import me.zbl.app.domain.Consumer;

import java.util.List;
import java.util.Map;

/**
 * 顾客业务接口
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-17
 */
public interface ConsumerService {

  List<Consumer> searchByName(String name);

  Consumer selectByPrimaryKey(String id);

  List<Consumer> selectAllConsumer(Map<String, Object> params);

  int count();

  int insertConsumer(Consumer consumer);

  int deleteConsumer(String id);

  int updateConsumer(Consumer consumer);
}
