package me.zbl.common.service;

import me.zbl.common.domain.LogDO;
import me.zbl.common.domain.PageDO;
import me.zbl.common.utils.Query;
import org.springframework.stereotype.Service;

@Service
public interface LogService {

  void save(LogDO logDO);

  PageDO<LogDO> queryList(Query query);

  int remove(Long id);

  int batchRemove(Long[] ids);
}
