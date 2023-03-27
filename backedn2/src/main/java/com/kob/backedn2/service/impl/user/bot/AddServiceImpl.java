package com.kob.backedn2.service.impl.user.bot;

import com.kob.backedn2.mapper.BotMapper;
import com.kob.backedn2.pojo.Bot;
import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {
    // 将接口注入进来
    @Autowired
    private BotMapper botMapper;// 使用Mapper操作数据库

    @Override
    public Map<String, String> add(Map<String, String> data) {

        // 从token中获取用户
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        // 根据key获取map中的数据
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        Map<String,String> map = new HashMap<>();

        if(title == null || title.length() == 0){
            map.put("error_message","标题不能为空");
            return map;
        }

        if(title.length() > 100){
            map.put("error_message","标题长度不能大于100");
            return map;
        }

        if(description == null || description.length() == 0){
            description = "这个用户很懒，什么也没留下";
        }

        if(description != null && description.length() > 300){
            map.put("error_message","Bot描述的长度不能大于300");
            return map;
        }

        if(content == null || content.length() == 0){
            map.put("error_message","代码不能为空");
            return map;
        }

        if(content.length() > 10000){
            map.put("error_message","代码长度不能超过10000");
            return map;
        }

        // 创建一个bot对象
        Date now = new Date();
        Bot bot = new Bot(null,user.getId(),title,description,content,1500,now,now);

        // 将Bot对象添加到数据库中
        botMapper.insert(bot);
        map.put("error_message","success");

        return map;
    }
}
