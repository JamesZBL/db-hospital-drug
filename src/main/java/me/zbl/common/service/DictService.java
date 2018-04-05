package me.zbl.common.service;

import me.zbl.common.domain.DictDO;
import me.zbl.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictService {

  DictDO get(Long id);

  List<DictDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(DictDO dict);

  int update(DictDO dict);

  int remove(Long id);

  int batchRemove(Long[] ids);

  List<DictDO> listType();

  String getName(String type, String value);

  /**
   * 获取爱好列表
   *
   * @param userDO
   *
   * @return
   */
  List<DictDO> getHobbyList(UserDO userDO);

  /**
   * 获取性别列表
   *
   * @return
   */
  List<DictDO> getSexList();

  /**
   * 根据type获取数据
   *
   * @param map
   *
   * @return
   */
  List<DictDO> listByType(String type);

}
