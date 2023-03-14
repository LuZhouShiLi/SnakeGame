package com.kob.backedn2.controller.pk;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 所有的请求都是在pk目录下面
@Controller
@RequestMapping("/pk/")
public class IndexController {

    // 返回index页面  templates pk 下面的inde4x.html
    @RequestMapping("index/")
    public String index(){
        return "pk/index.html";
    }
}
