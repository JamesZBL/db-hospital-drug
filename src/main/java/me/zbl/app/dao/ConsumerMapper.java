package me.zbl.app.dao;

import me.zbl.app.domain.Consumer;

public interface ConsumerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    Consumer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);
}