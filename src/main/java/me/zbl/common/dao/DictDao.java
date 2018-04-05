package me.zbl.common.dao;

import me.zbl.common.domain.DictDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface DictDao {

  DictDO get(Long id);

  List<DictDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(DictDO dict);

  int update(DictDO dict);

  int remove(Long id);

  int batchRemove(Long[] ids);

  List<DictDO> listType();
}
