package me.zbl.activity.service.impl;

import me.zbl.activity.config.ActivitiConstant;
import me.zbl.activity.dao.SalaryDao;
import me.zbl.activity.domain.SalaryDO;
import me.zbl.activity.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class SalaryServiceImpl implements SalaryService {

  @Autowired
  private SalaryDao salaryDao;
  @Autowired
  private ActTaskServiceImpl actTaskService;

  @Override
  public SalaryDO get(String id) {
    return salaryDao.get(id);
  }

  @Override
  public List<SalaryDO> list(Map<String, Object> map) {
    return salaryDao.list(map);
  }

  @Override
  public int count(Map<String, Object> map) {
    return salaryDao.count(map);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public int save(SalaryDO salary) {
    salary.setId(UUID.randomUUID().toString().replace("-", ""));
    actTaskService.startProcess(ActivitiConstant.ACTIVITI_SALARY[0], ActivitiConstant.ACTIVITI_SALARY[1], salary.getId(), salary.getContent(), new HashMap<>());
    return salaryDao.save(salary);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public int update(SalaryDO salary) {
    Map<String, Object> vars = new HashMap<>(16);
    vars.put("pass", salary.getTaskPass());
    vars.put("title", "");
    actTaskService.complete(salary.getTaskId(), vars);
    return salaryDao.update(salary);
  }

  @Override
  public int remove(String id) {
    return salaryDao.remove(id);
  }

  @Override
  public int batchRemove(String[] ids) {
    return salaryDao.batchRemove(ids);
  }

}
