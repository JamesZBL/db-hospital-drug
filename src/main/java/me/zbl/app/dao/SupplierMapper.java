package me.zbl.app.dao;

import me.zbl.app.domain.Supplier;

public interface SupplierMapper {
    int deleteByPrimaryKey(String id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);
}