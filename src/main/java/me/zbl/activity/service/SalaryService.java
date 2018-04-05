package me.zbl.activity.service;

import me.zbl.activity.domain.SalaryDO;

import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-11-25 13:33:16
 */
public interface SalaryService {

  SalaryDO get(String id);

  List<SalaryDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(SalaryDO salary);

  int update(SalaryDO salary);

  int remove(String id);

  int batchRemove(String[] ids);
}
