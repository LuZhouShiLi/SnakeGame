package com.kob.backedn2.service.impl.user.account;


import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.account.LoginService;
import com.kob.backedn2.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


// 添加注解service
@Service
public class LoginServiceImpl implements LoginService {

    // 需要用到的东西 都要注入进来  加上注解 autowired
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> getToken(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        // 登录失败 会自动处理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 登录成功 获得一个token对象

        // 取出用户信息
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();


        User user = loginUser.getUser();// 获取用户对象

        // 创建jwt token对象
        String jwt  = JwtUtil.createJWT(user.getId().toString());


        Map<String,String> map = new HashMap<>();

        map.put("error_message","success");

        map.put("token",jwt);







        return map;
    }
}
