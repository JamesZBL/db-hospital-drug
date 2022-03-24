package me.zbl.common.controller;

import me.zbl.common.config.Constant;
import me.zbl.common.domain.TaskDO;
import me.zbl.common.service.JobService;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-09-26 20:53:48
 */
@Controller
@RequestMapping("/common/job")
public abstract class JobController extends DeleteController {

  @Autowired
  private JobService taskScheduleJobService;

  @GetMapping()
  String taskScheduleJob() {
    return "common/job/job";
  }

  @ResponseBody
  @GetMapping("/list")
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    // 查询列表数据
    Query query = new Query(params);
    List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
    int total = taskScheduleJobService.count(query);
    PageWrapper pageWrapper = new PageWrapper(taskScheduleJobList, total);
    return pageWrapper;
  }

  @GetMapping("/add")
  String add() {
    return "common/job/add";
  }

  @GetMapping("/edit/{id}")
  String edit(@PathVariable("id") Long id, Model model) {
    TaskDO job = taskScheduleJobService.get(id);
    model.addAttribute("job", job);
    return "common/job/edit";
  }

  /**
   * 信息
   */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    TaskDO taskScheduleJob = taskScheduleJobService.get(id);
    return R.ok().put("taskScheduleJob", taskScheduleJob);
  }

  /**
   * 保存
   */
  @ResponseBody
  @PostMapping("/save")
  public R save(TaskDO taskScheduleJob) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    if (taskScheduleJobService.save(taskScheduleJob) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 修改
   */
  @ResponseBody
  @PostMapping("/update")
  public R update(TaskDO taskScheduleJob) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    taskScheduleJobService.update(taskScheduleJob);
    return R.ok();
  }

  /**
   * 删除
   */
  @Override
  public R removeRefactor(Long id) {

    if (taskScheduleJobService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 删除
   */
  @PostMapping("/batchRemove")
  @ResponseBody
  public R remove(@RequestParam("ids[]") Long[] ids) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    taskScheduleJobService.batchRemove(ids);

    return R.ok();
  }

  @PostMapping(value = "/changeJobStatus")
  @ResponseBody
  public R changeJobStatus(Long id, String cmd) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    String label = "停止";
    if ("start".equals(cmd)) {
      label = "启动";
    } else {
      label = "停止";
    }
    try {
      taskScheduleJobService.changeStatus(id, cmd);
      return R.ok("任务" + label + "成功");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return R.ok("任务" + label + "失败");
  }

}
