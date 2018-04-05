package me.zbl.oa.dao;

import me.zbl.oa.domain.NotifyDO;
import me.zbl.oa.domain.NotifyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-05 17:11:16
 */
@Mapper
public interface NotifyDao {

  NotifyDO get(Long id);

  List<NotifyDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(NotifyDO notify);

  int update(NotifyDO notify);

  int remove(Long id);

  int batchRemove(Long[] ids);

  List<NotifyDO> listByIds(Long[] ids);

  int countDTO(Map<String, Object> map);

  List<NotifyDTO> listDTO(Map<String, Object> map);
}
