package me.zbl.common.dao;

import me.zbl.common.domain.LogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface LogDao {

  LogDO get(Long id);

  List<LogDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(LogDO log);

  int update(LogDO log);

  int remove(Long id);

  int batchRemove(Long[] ids);
}
