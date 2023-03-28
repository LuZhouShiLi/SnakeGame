package com.kob.backedn2.controller.user.bot;


import com.kob.backedn2.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UpdateController {

    // 注入实现的接口 然后调用实现的接口板中的方法
    @Autowired
    private UpdateService updateService;

    // 将请求获取的data容器 作为参数 传入service接口中
    @PostMapping("/user/bot/update/")
    public Map<String,String> update(@RequestParam Map<String,String> data){
        return updateService.update(data);
    }
}
