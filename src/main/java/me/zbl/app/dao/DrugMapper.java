package me.zbl.app.dao;

import me.zbl.app.domain.Drug;

public interface DrugMapper {
    int deleteByPrimaryKey(String id);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);
}