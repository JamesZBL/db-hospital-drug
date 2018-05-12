package me.zbl.oa.controller;

import me.zbl.common.config.Constant;
import me.zbl.common.controller.BaseController;
import me.zbl.common.domain.DictDO;
import me.zbl.common.service.DictService;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import me.zbl.oa.domain.NotifyDO;
import me.zbl.oa.domain.NotifyRecordDO;
import me.zbl.oa.service.NotifyRecordService;
import me.zbl.oa.service.NotifyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends BaseController {

  @Autowired
  private NotifyService notifyService;
  @Autowired
  private NotifyRecordService notifyRecordService;
  @Autowired
  private DictService dictService;

  @GetMapping()
  @RequiresPermissions("oa:notify:notify")
  String oaNotify() {
    return "oa/notify/notify";
  }

  @ResponseBody
  @GetMapping("/list")
  @RequiresPermissions("oa:notify:notify")
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    // 查询列表数据
    Query query = new Query(params);
    List<NotifyDO> notifyList = notifyService.list(query);
    int total = notifyService.count(query);
    PageWrapper pageWrapper = new PageWrapper(notifyList, total);
    return pageWrapper;
  }

  @GetMapping("/add")
  @RequiresPermissions("oa:notify:add")
  String add() {
    return "oa/notify/add";
  }

  @GetMapping("/edit/{id}")
  @RequiresPermissions("oa:notify:edit")
  String edit(@PathVariable("id") Long id, Model model) {
    NotifyDO notify = notifyService.get(id);
    List<DictDO> dictDOS = dictService.listByType("oa_notify_type");
    String type = notify.getType();
    for (DictDO dictDO : dictDOS) {
      if (type.equals(dictDO.getValue())) {
        dictDO.setRemarks("checked");
      }
    }
    model.addAttribute("oaNotifyTypes", dictDOS);
    model.addAttribute("notify", notify);
    return "oa/notify/edit";
  }

  /**
   * 保存
   */
  @ResponseBody
  @PostMapping("/save")
  @RequiresPermissions("oa:notify:add")
  public R save(NotifyDO notify) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    notify.setCreateBy(getUserId());
    if (notifyService.save(notify) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 修改
   */
  @ResponseBody
  @RequestMapping("/update")
  @RequiresPermissions("oa:notify:edit")
  public R update(NotifyDO notify) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    notifyService.update(notify);
    return R.ok();
  }

  /**
   * 删除
   */
  @PostMapping("/remove")
  @ResponseBody
  @RequiresPermissions("oa:notify:remove")
  public R remove(Long id) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    if (notifyService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 删除
   */
  @PostMapping("/batchRemove")
  @ResponseBody
  @RequiresPermissions("oa:notify:batchRemove")
  public R remove(@RequestParam("ids[]") Long[] ids) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    notifyService.batchRemove(ids);
    return R.ok();
  }

  @ResponseBody
  @GetMapping("/message")
  PageWrapper message() {
    Map<String, Object> params = new HashMap<>(16);
    params.put("offset", 0);
    params.put("limit", 3);
    Query query = new Query(params);
    query.put("userId", getUserId());
    query.put("isRead", Constant.OA_NOTIFY_READ_NO);
    return notifyService.selfList(query);
  }

  @GetMapping("/selfNotify")
  String selefNotify() {
    return "oa/notify/selfNotify";
  }

  @ResponseBody
  @GetMapping("/selfList")
  PageWrapper selfList(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    query.put("userId", getUserId());

    return notifyService.selfList(query);
  }

  @GetMapping("/read/{id}")
  @RequiresPermissions("oa:notify:edit")
  String read(@PathVariable("id") Long id, Model model) {
    NotifyDO notify = notifyService.get(id);
    //更改阅读状态
    NotifyRecordDO notifyRecordDO = new NotifyRecordDO();
    notifyRecordDO.setNotifyId(id);
    notifyRecordDO.setUserId(getUserId());
    notifyRecordDO.setReadDate(new Date());
    notifyRecordDO.setIsRead(Constant.OA_NOTIFY_READ_YES);
    notifyRecordService.changeRead(notifyRecordDO);
    model.addAttribute("notify", notify);
    return "oa/notify/read";
  }


}
