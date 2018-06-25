package me.zbl.app.dao;

import me.zbl.app.domain.*;

import java.util.List;
import java.util.Map;

public interface InventoryMapper {

  int saleSave(DrugOutFormDO drugOutFormDO);

  int drugOutSave(DrugOutFormDO drugOutFormDO);

  int drugInSave(DrugInFormDO drugInFormDO);

  List<StaSaleDO> staSaleDay();

  List<StaSaleDO> staSaleMonth();

  List<StaSaleDO> staSaleYear();

  List<DrugInDO> inList(Map<String, Object> param);

  List<DrugOutDO> outList(Map<String, Object> param);

  List<SaleDO> saleList(Map<String, Object> param);

  int deleteByPrimaryKey(String id);

  int countIn();

  int countOut();

  int countSale();

  int insert(Inventory record);

  int insertSelective(Inventory record);

  Inventory selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Inventory record);

  int updateByPrimaryKey(Inventory record);
}