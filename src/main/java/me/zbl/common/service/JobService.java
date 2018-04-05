package me.zbl.common.service;

import me.zbl.common.domain.TaskDO;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-09-26 20:53:48
 */
public interface JobService {

  TaskDO get(Long id);

  List<TaskDO> list(Map<String, Object> map);

  int count(Map<String, Object> map);

  int save(TaskDO taskScheduleJob);

  int update(TaskDO taskScheduleJob);

  int remove(Long id);

  int batchRemove(Long[] ids);

  void initSchedule() throws SchedulerException;

  void changeStatus(Long jobId, String cmd) throws SchedulerException;

  void updateCron(Long jobId) throws SchedulerException;
}
