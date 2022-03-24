package me.zbl.activity.service.impl;

import me.zbl.activity.service.ProcessStartService;
import me.zbl.common.utils.ShiroUtils;
import me.zbl.common.utils.StringUtils;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ProcessStartServiceImpl implements ProcessStartService {

    @Autowired
    IdentityService identityService;

    @Autowired
    RuntimeService runtimeService;


    private final ActTaskServiceImpl actTaskServiceImpl;

    public ProcessStartServiceImpl(ActTaskServiceImpl actTaskServiceImpl) {
        this.actTaskServiceImpl = actTaskServiceImpl;
    }


    /**
     * 启动流程
     *
     * @param procDefKey    流程定义KEY
     * @param businessTable 业务表表名
     * @param businessId    业务表编号
     * @param title         流程标题，显示在待办任务标题
     * @param vars          流程变量
     * @return 流程实例ID
     */
    @Override
    public String startProcess(String procDefKey, String businessTable, String businessId, String title, Map<String, Object> vars) {
        String userId = ShiroUtils.getUser().getUsername();//ObjectUtils.toString(UserUtils.getUser().getId())

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        // 设置流程变量
        if (vars == null) {
            vars = new HashMap();
        }

        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }

        // 启动流程
        ProcessInstance procIns = runtimeService.startProcessInstanceByKey(procDefKey, businessId, vars);

        return null;
    }
}