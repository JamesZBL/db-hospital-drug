package me.zbl.app.dao;

import me.zbl.app.domain.Consumer;

import java.util.List;
import java.util.Map;

public interface ConsumerMapper {

  List<Consumer> selectByName(String name);

  List<Consumer> selectAllConsumer(Map<String, Object> params);

  Consumer selectConsumerByTel(String tel);

  int count();

  int deleteByPrimaryKey(String id);

  int insert(Consumer record);

  int insertSelective(Consumer record);

  Consumer selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Consumer record);

  int updateByPrimaryKey(Consumer record);
}