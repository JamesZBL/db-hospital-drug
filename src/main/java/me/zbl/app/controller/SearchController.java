package me.zbl.app.controller;

import me.zbl.app.service.DrugOutService;
import me.zbl.common.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 查询
 */
@Controller
public class SearchController {

    @Autowired
    DrugOutService drugOutService;

    @GetMapping("/search/consumer/index")
    public String consumerIndex() {
        return "app/search/consumer/index";
    }

    @GetMapping("/search/drug/index")
    public String drugIndex() {
        return "app/search/drug/index";
    }

    @GetMapping("/search/supplier/index")
    public String supplierIndex() {
        return "app/search/supplier/index";
    }

    @GetMapping("/search/return/index")
    public String returnIndex() {
        return "app/search/return/index";
    }

    @GetMapping("/search/accounts/index")
    public String accountsIndex() {
        return "app/search/accounts/index";
    }

    @GetMapping("/search/return")
    @ResponseBody
    public PageWrapper returnList(@RequestParam Map<String,Object> params) {
        return drugOutService.returnList(params);
    }

    @GetMapping("/search/accounts")
    @ResponseBody
    public PageWrapper accountsList(@RequestParam Map<String,Object> params) {
        return drugOutService.accountsList(params);
    }
}
