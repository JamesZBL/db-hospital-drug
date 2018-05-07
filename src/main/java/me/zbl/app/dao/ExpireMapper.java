package me.zbl.app.dao;

import me.zbl.app.domain.Expire;

public interface ExpireMapper {
    int deleteByPrimaryKey(String id);

    int insert(Expire record);

    int insertSelective(Expire record);

    Expire selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Expire record);

    int updateByPrimaryKey(Expire record);
}