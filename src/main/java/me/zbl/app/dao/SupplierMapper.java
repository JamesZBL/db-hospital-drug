package me.zbl.app.dao;

import me.zbl.app.domain.Supplier;

import java.util.List;
import java.util.Map;

public interface SupplierMapper {

  List<Supplier> selectAllSupplier(Map<String, Object> params);

  int count();

  int deleteByPrimaryKey(String id);

  int insert(Supplier record);

  int insertSelective(Supplier record);

  Supplier selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Supplier record);

  int updateByPrimaryKey(Supplier record);
}