package com.kob.backedn2.controller.user.account;


import com.kob.backedn2.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
//   注入service的接口
    @Autowired
    private LoginService loginService;

    // 登录是post请求  将该链接公开化
    @PostMapping("/user/account/token/")
    public Map<String,String> getToken(@RequestParam Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        System.out.println(username);
        // 根据用户名和密码 获取token
        return loginService.getToken(username,password);
    }


}
