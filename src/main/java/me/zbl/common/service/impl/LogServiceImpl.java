package me.zbl.common.service.impl;

import me.zbl.common.dao.LogDao;
import me.zbl.common.domain.LogDO;
import me.zbl.common.domain.PageDO;
import me.zbl.common.service.LogService;
import me.zbl.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

  @Autowired
  LogDao logMapper;

  @Async
  @Override
  public void save(LogDO logDO) {
    logMapper.save(logDO);
  }

  @Override
  public PageDO<LogDO> queryList(Query query) {
    int total = logMapper.count(query);
    List<LogDO> logs = logMapper.list(query);
    PageDO<LogDO> page = new PageDO<>();
    page.setTotal(total);
    page.setRows(logs);
    return page;
  }

  @Override
  public int remove(Long id) {
    int count = logMapper.remove(id);
    return count;
  }

  @Override
  public int batchRemove(Long[] ids) {
    return logMapper.batchRemove(ids);
  }
}
