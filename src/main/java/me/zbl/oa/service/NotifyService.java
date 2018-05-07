package me.zbl.oa.service;

import me.zbl.common.utils.PageWrapper;
import me.zbl.oa.domain.NotifyDO;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-05 17:11:16
 */
public interface NotifyService {

  NotifyDO get(Long id);

  List<NotifyDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(NotifyDO notify);

  int update(NotifyDO notify);

  int remove(Long id);

  int batchRemove(Long[] ids);

  //	Map<String, Object> message(Long userId);

  PageWrapper selfList(Map<String, Object> map);
}
