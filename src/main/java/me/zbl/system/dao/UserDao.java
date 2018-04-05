package me.zbl.system.dao;

import me.zbl.system.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

  UserDO get(Long userId);

  List<UserDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(UserDO user);

  int update(UserDO user);

  int remove(Long userId);

  int batchRemove(Long[] userIds);

  Long[] listAllDept();

}
