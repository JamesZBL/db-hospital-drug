package me.zbl.system.dao;

import me.zbl.system.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface RoleDao {

  RoleDO get(Long roleId);

  List<RoleDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(RoleDO role);

  int update(RoleDO role);

  int remove(Long roleId);

  int batchRemove(Long[] roleIds);
}
