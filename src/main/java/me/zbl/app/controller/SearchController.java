package me.zbl.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 查询
 */
@Controller
public class SearchController {

    @GetMapping("/search/consumer/index")
    public String consumerIndex() {
        return "app/search/consumer/index";
    }

    @GetMapping("/search/drug/index")
    public String drugIndex() {
        return "app/search/drug/index";
    }

    @GetMapping("/search/supplier/index")
    public String index() {
        return "app/search/supplier/index";
    }
}
