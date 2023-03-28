package com.kob.backedn2.service.impl.user.bot;

import com.kob.backedn2.mapper.BotMapper;
import com.kob.backedn2.pojo.Bot;
import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private BotMapper botMapper;

    // data是从前端获取的容器 键值对
    @Override
    public Map<String, String> update(Map<String, String> data) {
        // 现根据token知道自己是谁 获取user
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) usernamePasswordAuthenticationToken.getPrincipal();
        User user = loginUser.getUser();

        // 然后获取前端返回的数据  首先根据data中的bot_id 获取id
        int bot_id = Integer.parseInt(data.get("bot_id"));// 获取的是字符串  然后解析成int数据

        // 从data数据中获取title description  content三个信息  填充到新的Bot记录 然后使用Mapper接口 插入到数据库
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        Map<String,String> map = new HashMap<>();

        // 根据前端解析出来的bot_id  使用botMapper接口 查询数据库 返回一个bot记录
        Bot bot = botMapper.selectById(bot_id);

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

        if(bot == null){
            map.put("error_message","Bot不存在或者已经删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())){
            map.put("error_message","没有权限修改Bot");
            return  map;
        }

        Bot new_bot = new Bot(bot.getId(),user.getId(),title,description,content,bot.getRating(),bot.getCreatetime(),new Date());

        // 调用接口 更新Bot
        botMapper.updateById(new_bot);
        map.put("error_message","success");


        return map;
    }
}
