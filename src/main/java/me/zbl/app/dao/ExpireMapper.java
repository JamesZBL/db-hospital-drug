package me.zbl.app.dao;

import me.zbl.app.domain.Expire;

import java.util.Date;
import java.util.List;

public interface ExpireMapper {

  List<Expire> selectByDate(Date date);

  int deleteByPrimaryKey(String id);

  int insert(Expire record);

  int insertSelective(Expire record);

  Expire selectByPrimaryKey(String id);

  int updateByPrimaryKeySelective(Expire record);

  int updateByPrimaryKey(Expire record);
}