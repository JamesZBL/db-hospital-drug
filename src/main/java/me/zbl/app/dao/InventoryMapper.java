package me.zbl.app.dao;

import me.zbl.app.domain.DrugInDO;
import me.zbl.app.domain.DrugInFormDO;
import me.zbl.app.domain.DrugOutDO;
import me.zbl.app.domain.Inventory;

import java.util.List;
import java.util.Map;

public interface InventoryMapper {

  int drugInSave(DrugInFormDO drugInFormDO);

  List<DrugInDO> inList(Map<String, Object> param);

  List<DrugOutDO> outList(Map<String, Object> param);

  int deleteByPrimaryKey(String id);

  int countIn();

  int countOut();

  int insert(Inventory record);

  int insertSelective(Inventory record);

  Inventory selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Inventory record);

  int updateByPrimaryKey(Inventory record);
}