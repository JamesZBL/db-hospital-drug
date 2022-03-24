package me.zbl.common.controller;

import me.zbl.common.config.Constant;
import me.zbl.common.service.DictService;
import me.zbl.common.utils.R;
import me.zbl.oa.controller.NotifyController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public abstract class DeleteController extends BaseController {

    @Autowired
    NotifyController notifyController;

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id){

        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        else
           return removeRefactor(id); // Template method - variation point

    }

    protected abstract R removeRefactor(Long id);
}
