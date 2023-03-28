package com.kob.backedn2.service.impl.user.bot;

import com.kob.backedn2.mapper.BotMapper;
import com.kob.backedn2.pojo.Bot;
import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private BotMapper botMapper;

    // 浏览器输入网址 之后 回车  返回data数据 data数据是一个键值对容器  通过bot_id key获取值
    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        User user = loginUser.getUser();// 获取用户
        int bot_id = Integer.parseInt(data.get("bot_id"));// 获取bot的id

        // 获取bot  通过bot_id 获取数据库的bot记录
        Bot bot = botMapper.selectById(bot_id);

        Map<String,String> map = new HashMap<>();

        if(bot == null){
            map.put("error_message","Bot不存在或者已经被删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            map.put("error_message","没有权限删除bot");
            return map;
        }

        // 删除
        botMapper.deleteById(bot_id);

        map.put("error_message","success");


        return null;
    }
}
