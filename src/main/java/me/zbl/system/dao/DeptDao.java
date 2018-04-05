package me.zbl.system.dao;

import me.zbl.system.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
public interface DeptDao {

  DeptDO get(Long deptId);

  List<DeptDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(DeptDO dept);

  int update(DeptDO dept);

  int remove(Long deptId);

  int batchRemove(Long[] deptIds);

  Long[] listParentDept();

  int getDeptUserNumber(Long deptId);
}
