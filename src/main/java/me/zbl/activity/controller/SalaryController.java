package me.zbl.activity.controller;

import me.zbl.activity.domain.SalaryDO;
import me.zbl.activity.service.SalaryService;
import me.zbl.activity.utils.ActivitiUtils;
import me.zbl.common.config.Constant;
import me.zbl.common.controller.BaseController;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import me.zbl.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-11-25 13:33:16
 */

@Controller
@RequestMapping("/act/salary")
public class SalaryController extends BaseController {

  @Autowired
  ActivitiUtils activitiUtils;
  @Autowired
  private SalaryService salaryService;

  @GetMapping()
  String Salary() {
    return "activity/salary/salary";
  }

  @ResponseBody
  @GetMapping("/list")
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<SalaryDO> salaryList = salaryService.list(query);
    int total = salaryService.count(query);
    PageWrapper pageWrapper = new PageWrapper(salaryList, total);
    return pageWrapper;
  }

  @GetMapping("/form")
  String add() {
    return "act/salary/add";
  }

  @GetMapping("/form/{taskId}")
  String edit(@PathVariable("taskId") String taskId, Model model) {
    SalaryDO salary = salaryService.get(activitiUtils.getBusinessKeyByTaskId(taskId));
    salary.setTaskId(taskId);
    model.addAttribute("salary", salary);
    return "act/salary/edit";
  }

  /**
   * 保存
   */
  @ResponseBody
  @PostMapping("/save")
  public R saveOrUpdate(SalaryDO salary) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    salary.setCreateDate(new Date());
    salary.setUpdateDate(new Date());
    salary.setCreateBy(ShiroUtils.getUserId().toString());
    salary.setUpdateBy(ShiroUtils.getUserId().toString());
    salary.setDelFlag("1");
    if (salaryService.save(salary) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 修改
   */
  @ResponseBody
  @RequestMapping("/update")
  public R update(SalaryDO salary) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    String taskKey = activitiUtils.getTaskByTaskId(salary.getTaskId()).getTaskDefinitionKey();
    if ("audit2".equals(taskKey)) {
      salary.setHrText(salary.getTaskComment());
    } else if ("audit3".equals(taskKey)) {
      salary.setLeadText(salary.getTaskComment());
    } else if ("audit4".equals(taskKey)) {
      salary.setMainLeadText(salary.getTaskComment());
    } else if ("apply_end".equals(salary.getTaskComment())) {
      //流程完成，兑现
    }
    salaryService.update(salary);
    return R.ok();
  }

  /**
   * 删除
   */
  @PostMapping("/remove")
  @ResponseBody
  public R remove(String id) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    if (salaryService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 删除
   */
  @PostMapping("/batchRemove")
  @ResponseBody
  public R remove(@RequestParam("ids[]") String[] ids) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    salaryService.batchRemove(ids);
    return R.ok();
  }

}
