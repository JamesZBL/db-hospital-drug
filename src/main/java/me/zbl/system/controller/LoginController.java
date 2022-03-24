package me.zbl.system.controller;

import me.zbl.common.annotation.Log;
import me.zbl.common.controller.BaseController;
import me.zbl.common.domain.FileDO;
import me.zbl.common.domain.Tree;
import me.zbl.common.service.FileService;
import me.zbl.common.utils.MD5Utils;
import me.zbl.common.utils.R;
import me.zbl.common.utils.ShiroUtils;
import me.zbl.system.domain.MenuDO;
import me.zbl.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController extends BaseController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  MenuService menuService;
  @Autowired
  FileService fileService;


  @GetMapping({"/", ""})
  String welcome(Model model) {

    return "redirect:/login";
  }

  @Log("请求访问主页")
  @GetMapping({"/index"})
  String index(Model model) {
    return menuService.getMenus(model);
  }

  @GetMapping("/login")
  String login() {
    return "login";
  }

  @Log("登录")
  @PostMapping("/login")
  @ResponseBody
  R ajaxLogin(String username, String password) {

    password = MD5Utils.encrypt(username, password);
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    Subject subject = SecurityUtils.getSubject();
    try {
      subject.login(token);
      return R.ok();
    } catch (AuthenticationException e) {
      return R.error("用户或密码错误");
    }
  }

  @GetMapping("/logout")
  String logout() {
    ShiroUtils.logout();
    return "redirect:/login";
  }

  @GetMapping("/main")
  String main() {
    return "main";
  }

}
