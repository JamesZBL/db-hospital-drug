package me.zbl.common.controller;

import me.zbl.common.utils.ShiroUtils;
import me.zbl.system.domain.UserDO;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

  public UserDO getUser() {
    return ShiroUtils.getUser();
  }

  public Long getUserId() {
    return getUser().getUserId();
  }

  public String getUsername() {
    return getUser().getUsername();
  }
}