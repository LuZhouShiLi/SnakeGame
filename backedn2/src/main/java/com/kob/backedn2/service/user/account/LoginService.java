package com.kob.backedn2.service.user.account;

import java.util.Map;

public interface LoginService {

//     返回登录信息
    public Map<String,String> getToken(String username, String password);
}
