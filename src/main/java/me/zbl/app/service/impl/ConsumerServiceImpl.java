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

import me.zbl.app.dao.ConsumerMapper;
import me.zbl.app.domain.Consumer;
import me.zbl.app.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 顾客业务实现
 *
 * @author JamesZBL
 * @email 1146556298@qq.com
 * @date 2018-06-19
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

  @Autowired
  private ConsumerMapper consumerMapper;

  @Override
  public List<Consumer> searchByName(String name) {
    return consumerMapper.selectByName(name);
  }

  @Override
  public Consumer selectByPrimaryKey(String id) {
    return consumerMapper.selectByPrimaryKey(id);
  }

  @Override
  public List<Consumer> selectAllConsumer(Map<String, Object> params) {
    return consumerMapper.selectAllConsumer(params);
  }

  @Override
  public int count() {
    return consumerMapper.count();
  }

  @Override
  public int insertConsumer(Consumer consumer) {
    Optional<Consumer> find = Optional.ofNullable(consumerMapper.selectConsumerByTel(consumer.getTel()));
    if (find.isPresent()) {
      throw new IllegalArgumentException("用户电话号码已存在");
    }
    return consumerMapper.insertSelective(consumer);
  }

  @Override
  public int deleteConsumer(String id) {
    return consumerMapper.deleteByPrimaryKey(id);
  }

  @Override
  public int updateConsumer(Consumer consumer) {
    return consumerMapper.updateByPrimaryKeySelective(consumer);
  }
}
