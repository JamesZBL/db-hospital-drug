package me.zbl.system.dao;

import me.zbl.system.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserRoleDao {

  UserRoleDO get(Long id);

  List<UserRoleDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(UserRoleDO userRole);

  int update(UserRoleDO userRole);

  int remove(Long id);

  int batchRemove(Long[] ids);

  List<Long> listRoleId(Long userId);

  int removeByUserId(Long userId);

  int removeByRoleId(Long roleId);

  int batchSave(List<UserRoleDO> list);

  int batchRemoveByUserId(Long[] ids);
}
