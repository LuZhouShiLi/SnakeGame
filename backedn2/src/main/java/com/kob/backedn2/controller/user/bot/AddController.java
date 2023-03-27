package com.kob.backedn2.controller.user.bot;

import com.kob.backedn2.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddController {

    // 将实现的service接口进行注入
    @Autowired
    private AddService addService;

    // 用户访问该url路径 获取资源
    @PostMapping("/user/bot/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){

        // 调用service 插入数据
        return addService.add(data);
    }
}
