package me.zbl.blog.controller;

import me.zbl.blog.domain.ContentDO;
import me.zbl.blog.service.ContentService;
import me.zbl.common.config.Constant;
import me.zbl.common.controller.BaseController;
import me.zbl.common.utils.PageWrapper;
import me.zbl.common.utils.Query;
import me.zbl.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 文章内容
 *
 * @author 郑保乐
 * @email 18333298410@163.com
 * @date 2017-09-09 10:03:34
 */
@Controller
@RequestMapping("/blog/bContent")
public class ContentController extends BaseController {

  @Autowired
  ContentService bContentService;

  @GetMapping()
  @RequiresPermissions("blog:bContent:bContent")
  String bContent() {
    return "blog/bContent/bContent";
  }

  @ResponseBody
  @GetMapping("/list")
  @RequiresPermissions("blog:bContent:bContent")
  public PageWrapper list(@RequestParam Map<String, Object> params) {
    Query query = new Query(params);
    List<ContentDO> bContentList = bContentService.list(query);
    int total = bContentService.count(query);
    PageWrapper pageWrapper = new PageWrapper(bContentList, total);
    return pageWrapper;
  }

  @GetMapping("/add")
  @RequiresPermissions("blog:bContent:add")
  String add() {
    return "blog/bContent/add";
  }

  @GetMapping("/edit/{cid}")
  @RequiresPermissions("blog:bContent:edit")
  String edit(@PathVariable("cid") Long cid, Model model) {
    ContentDO bContentDO = bContentService.get(cid);
    model.addAttribute("bContent", bContentDO);
    return "blog/bContent/edit";
  }

  /**
   * 保存
   */
  @ResponseBody
  @RequiresPermissions("blog:bContent:add")
  @PostMapping("/save")
  public R save(ContentDO bContent) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    if (bContent.getAllowComment() == null) {
      bContent.setAllowComment(0);
    }
    if (bContent.getAllowFeed() == null) {
      bContent.setAllowFeed(0);
    }
    if (null == bContent.getType()) {
      bContent.setType("article");
    }
    bContent.setGtmCreate(new Date());
    bContent.setGtmModified(new Date());
    int count;
    if (bContent.getCid() == null || "".equals(bContent.getCid())) {
      count = bContentService.save(bContent);
    } else {
      count = bContentService.update(bContent);
    }
    if (count > 0) {
      return R.ok().put("cid", bContent.getCid());
    }
    return R.error();
  }

  /**
   * 修改
   */
  @RequiresPermissions("blog:bContent:edit")
  @ResponseBody
  @RequestMapping("/update")
  public R update(ContentDO bContent) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    bContent.setGtmCreate(new Date());
    bContentService.update(bContent);
    return R.ok();
  }

  /**
   * 删除
   */
  @RequiresPermissions("blog:bContent:remove")
  @PostMapping("/remove")
  @ResponseBody
  public R remove(Long id) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    if (bContentService.remove(id) > 0) {
      return R.ok();
    }
    return R.error();
  }

  /**
   * 删除
   */
  @RequiresPermissions("blog:bContent:batchRemove")
  @PostMapping("/batchRemove")
  @ResponseBody
  public R remove(@RequestParam("ids[]") Long[] cids) {
    if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
      return R.error(1, "演示系统不允许修改,完整体验请部署程序");
    }
    bContentService.batchRemove(cids);
    return R.ok();
  }
}
