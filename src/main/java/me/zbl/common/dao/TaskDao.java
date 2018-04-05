package me.zbl.common.dao;

import me.zbl.common.domain.TaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface TaskDao {

  TaskDO get(Long id);

  List<TaskDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(TaskDO task);

  int update(TaskDO task);

  int remove(Long id);

  int batchRemove(Long[] ids);
}
