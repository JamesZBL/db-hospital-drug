package me.zbl.app.dao;

import me.zbl.app.domain.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryMapper {

  List<Inventory> list(Map<String, Object> param);

  int deleteByPrimaryKey(String id);

  int insert(Inventory record);

  int insertSelective(Inventory record);

  Inventory selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Inventory record);

  int updateByPrimaryKey(Inventory record);
}