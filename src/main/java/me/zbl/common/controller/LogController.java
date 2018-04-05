package me.zbl.common.controller;

import me.zbl.common.domain.LogDO;
import me.zbl.common.domain.PageDO;
import me.zbl.common.service.LogService;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/common/log")
@Controller
public class LogController {

  @Autowired
  LogService logService;
  String prefix = "common/log";

  @GetMapping()
  String log() {
    return prefix + "/log";
  }

  @ResponseBody
  @GetMapping("/list")
  PageDO<LogDO> list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    PageDO<LogDO> page = logService.queryList(query);
    return page;
  }

  @ResponseBody
  @PostMapping("/remove")
  R remove(Long id) {
    if (logService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  @ResponseBody
  @PostMapping("/batchRemove")
  R batchRemove(@RequestParam("ids[]") Long[] ids) {
    int r = logService.batchRemove(ids);
    if (r > 0) {
      return R.ok();
    }
    return R.error();
  }
}
