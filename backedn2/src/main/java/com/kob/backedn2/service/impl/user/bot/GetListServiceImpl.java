package com.kob.backedn2.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backedn2.mapper.BotMapper;
import com.kob.backedn2.pojo.Bot;
import com.kob.backedn2.pojo.User;
import com.kob.backedn2.service.impl.utils.UserDetailsImpl;
import com.kob.backedn2.service.user.bot.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private BotMapper botMapper;// 注入数据库查询接口
    @Override
    public List<Bot> getList() {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Bot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());// 根据user_id 获取查询结果


        return botMapper.selectList(queryWrapper);
    }
}
